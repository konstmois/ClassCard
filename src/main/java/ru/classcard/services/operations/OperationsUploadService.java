package ru.classcard.services.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.classcard.dao.CardDAO;
import ru.classcard.dao.CardOperationDAO;
import ru.classcard.model.Card;
import ru.classcard.model.CardOperation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ZERO;
import static ru.classcard.model.OperationType.EXPENSE;
import static ru.classcard.model.OperationType.INCOME;

public class OperationsUploadService {

    private static final String SPLIT_BY = ",";

    @Autowired
    private CardOperationDAO dao;

    @Autowired
    private CardDAO cardDAO;

    @Transactional
    public void uploadOperations(Card card, InputStream stream) throws Exception {
        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        br.readLine();
        List<CardOperation> operationList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] operationFields = line.split(SPLIT_BY);
            operationList.add(createCardOperation(card, operationFields));
        }
        dao.saveList(operationList);
        cardDAO.save(card);
    }

    private CardOperation createCardOperation(Card card, String[] operationFields) throws ParseException, UnsupportedEncodingException {
        CardOperation op = new CardOperation();

        setCard(card, operationFields, op);
        setOperationDate(operationFields, op);
        setAmountAndUpdateBalance(card, operationFields, op);
        op.setMcc(operationFields[5].trim());
        op.setDesc(operationFields[8].trim());

        return op;
    }

    private void setAmountAndUpdateBalance(Card card, String[] operationFields, CardOperation op) {
        BigDecimal amount = new BigDecimal(operationFields[11].trim().replaceAll(" ", ""));
        card.setBalance(card.getBalance().add(amount));
        op.setAmount(amount);
        op.setType(amount.compareTo(ZERO) > 0 ? INCOME : EXPENSE);
    }

    private void setOperationDate(String[] operationFields, CardOperation op) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        op.setDate(format.parse(operationFields[2].trim()));
    }

    private void setCard(Card card, String[] operationFields, CardOperation op) {
        String cardNumber = operationFields[1].trim();
        if (!cardNumber.contains(card.getNumber())) {
            throw new IllegalArgumentException();
        }
        op.setCard(card);
    }

}
