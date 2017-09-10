package ru.classcard.services.operations;

import org.springframework.beans.factory.annotation.Autowired;
import ru.classcard.dao.CardOperationDAO;
import ru.classcard.dao.MccCodeDAO;
import ru.classcard.model.Card;
import ru.classcard.model.CardOperation;
import ru.classcard.model.ExpenseCategory;
import ru.classcard.model.MccCode;
import ru.classcard.types.DateRange;

import java.io.UnsupportedEncodingException;
import java.util.*;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.getInstance;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toMap;
import static ru.classcard.model.OperationType.EXPENSE;

public class ExpenseService {

    private static final String UTF_8 = "UTF-8";
    private static final String OTHER_CATEGORY_NAME = "Другое";
    private static final String OPERATION_TYPE_FIELD = "type";
    private static final String OPERATION_DATE_FIELD = "date";

    @Autowired
    private CardOperationDAO operationDAO;

    @Autowired
    private MccCodeDAO mccCodeDAO;

    public Map<String, Number> getExpenseMapping(Card card, Date month) {
        Map<String, Number> expenseMap = new HashMap<>();
        List<CardOperation> expenses = operationDAO.getOperationsBy(card, -1, -1, null, null, createFiltersForExpenseMapping(month));
        if (!expenses.isEmpty()) {
            List<MccCode> mccList = mccCodeDAO.getListByCodes(getMccCodesFrom(expenses));
            Map<String, ExpenseCategory> mccToCategory = mccList.stream().collect(toMap(MccCode::getCode, MccCode::getCategory));


            double amountOfOtherExpenses = 0;
            for (CardOperation expense : expenses) {
                double amount = expense.getAmount().abs().doubleValue();
                ExpenseCategory cat = mccToCategory.get(expense.getMcc());
                if (cat != null) {
                    addExpensesToCategory(expenseMap, amount, cat);
                } else {
                    amountOfOtherExpenses+=amount;
                }
            }

            addOtherExpenses(expenseMap, amountOfOtherExpenses);
        }
        return expenseMap;
    }

    private HashMap<String, Object> createFiltersForExpenseMapping(Date month) {
        HashMap<String, Object> filter = new HashMap<>();
        filter.put(OPERATION_TYPE_FIELD, EXPENSE);
        filter.put(OPERATION_DATE_FIELD, getMonthDateRange(month));
        return filter;
    }

    private void addOtherExpenses(Map<String, Number> expenseMap, double amountOfOtherExpenses) {
        if (amountOfOtherExpenses > 0) {
            expenseMap.put(fixEncoding(OTHER_CATEGORY_NAME), amountOfOtherExpenses);
        }
    }

    private void addExpensesToCategory(Map<String, Number> expenseMap, double amount, ExpenseCategory cat) {
        if (expenseMap.get(cat.getDesc()) == null) {
            expenseMap.put(cat.getDesc(), amount);
        } else {
            expenseMap.put(cat.getDesc(), expenseMap.get(cat.getDesc()).doubleValue() + amount);
        }
    }

    private Collection<String> getMccCodesFrom(List<CardOperation> operations) {
        return operations.stream().map(CardOperation::getMcc).collect(toCollection(HashSet::new));
    }

    private DateRange getMonthDateRange(Date month) {
        Calendar first = getInstance();
        first.setTimeInMillis(month.getTime());
        first.set(DAY_OF_MONTH, 1);

        Calendar last = getInstance();
        last.setTimeInMillis(month.getTime());
        last.set(DAY_OF_MONTH, last.getActualMaximum(DAY_OF_MONTH));

        DateRange range = new DateRange();
        range.setFrom(first.getTime());
        range.setTo(last.getTime());
        return range;
    }

    //TODO wtf with views?
    private String fixEncoding(String str) {
        try {
            return new String(str.getBytes(), UTF_8);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

}
