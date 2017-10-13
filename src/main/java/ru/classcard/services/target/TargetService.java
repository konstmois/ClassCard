package ru.classcard.services.target;

import org.springframework.beans.factory.annotation.Autowired;
import ru.classcard.dao.CardOperationDAO;
import ru.classcard.dao.StudentDAO;
import ru.classcard.model.*;
import ru.classcard.types.DateRange;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

import static java.lang.Boolean.*;
import static java.math.BigDecimal.*;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.getInstance;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static ru.classcard.model.OperationType.EXPENSE;
import static ru.classcard.model.OperationType.INCOME;

public class TargetService {

    private static final String UTF_8 = "UTF-8";
    private static final String OTHER_CATEGORY_NAME = "Другое";
    private static final String OPERATION_TYPE_FIELD = "type";
    private static final String OPERATION_DATE_FIELD = "date";
    private static final String OPERATION_IS_REST_FIELD= "rest";

    @Autowired
    private CardOperationDAO operationDAO;

    @Autowired
    private StudentDAO studentDAO;

    public List<TargetIncome> getIncomeGroupedByStudent(Target target) {
        Map<Student, BigDecimal> studentsIncome = studentDAO.findBy(target.getStudentClass())
                                                             .stream()
                                                             .collect(toMap(s -> s, s-> new BigDecimal(0)));

        for (CardOperation op : operationDAO.findIncomesBy(target)) {
            addOperationTo(studentsIncome, op);
        }
        return studentsIncome.entrySet()
                .stream()
                .map(e -> new TargetIncome(e.getKey(), e.getValue()))
                .collect(toList());
    }

    private void addOperationTo(Map<Student, BigDecimal> studentsIncome, CardOperation op) {
        if (op.getStudent() != null) {
            Student st = op.getStudent();
            if (studentsIncome.get(st) != null) {
                studentsIncome.put(st, studentsIncome.get(st).add(op.getAmount()));
            } else {
                studentsIncome.put(st, op.getAmount());
            }
        }
    }

    public BigDecimal calcExpense(Target target) {
        BigDecimal expenses = operationDAO.findExpensesBy(target).stream()
                                          .map(CardOperation::getAmount)
                                          .reduce(ZERO, BigDecimal::add);

        BigDecimal restIncomes = operationDAO.findRestIncomesBy(target).stream()
                                             .map(CardOperation::getAmount)
                                             .reduce(ZERO, BigDecimal::add);
        return expenses.add(restIncomes);
    }

    public BigDecimal calcIncome(Target target) {
        return operationDAO.findIncomesBy(target)
                .stream()
                .map(CardOperation::getAmount)
                .reduce(ZERO, BigDecimal::add);
    }

    public Map<String, Number> getExpenseToTargetMapping(Card card, Date month) {
        Map<String, Number> expenseMap = new HashMap<>();
        List<CardOperation> expenses = operationDAO.getOperationsBy(card, -1, -1, null, null, createFiltersForExpenseMapping(month));
        List<CardOperation> restIncomes = operationDAO.getOperationsBy(card, -1, -1, null, null, createFiltersForRestIncomes(month));

        mapExpenses(expenseMap, expenses);
        mapRestIncomes(expenseMap, restIncomes);

        return expenseMap;
    }

    private void mapRestIncomes(Map<String, Number> expenseMap, List<CardOperation> restIncomes) {
        for (CardOperation income : restIncomes) {
            Target target = income.getTarget();
            double amount = income.getAmount().abs().doubleValue();
            if (target != null && expenseMap.get(target.getName()) != null ) {
                expenseMap.put(target.getName(), expenseMap.get(target.getName()).doubleValue() - amount);
            }
        }
    }

    private void mapExpenses(Map<String, Number> expenseMap, List<CardOperation> expenses) {
        if (!expenses.isEmpty()) {
            double amountOfOtherExpenses = 0;
            for (CardOperation expense : expenses) {
                double amount = expense.getAmount().abs().doubleValue();
                Target target = expense.getTarget();
                if (target != null) {
                    addExpensesToTarget(expenseMap, amount, target);
                } else {
                    amountOfOtherExpenses+=amount;
                }
            }
            addOtherExpenses(expenseMap, amountOfOtherExpenses);
        }
    }

    private Map<String, Object> createFiltersForRestIncomes(Date month) {
        HashMap<String, Object> filter = new HashMap<>();
        filter.put(OPERATION_TYPE_FIELD, INCOME);
        filter.put(OPERATION_IS_REST_FIELD, TRUE);
        filter.put(OPERATION_DATE_FIELD, getMonthDateRange(month));
        return filter;
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

    private void addExpensesToTarget(Map<String, Number> expenseMap, double amount, Target target) {
        if (expenseMap.get(target.getName()) == null) {
            expenseMap.put(target.getName(), amount);
        } else {
            expenseMap.put(target.getName(), expenseMap.get(target.getName()).doubleValue() + amount);
        }
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
