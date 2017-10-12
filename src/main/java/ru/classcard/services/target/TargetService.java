package ru.classcard.services.target;

import org.springframework.beans.factory.annotation.Autowired;
import ru.classcard.dao.CardOperationDAO;
import ru.classcard.dao.StudentDAO;
import ru.classcard.model.CardOperation;
import ru.classcard.model.Student;
import ru.classcard.model.Target;
import ru.classcard.model.TargetIncome;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.*;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

public class TargetService {

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
        return operationDAO.findExpensesBy(target).stream()
                .map(op -> op.getAmount())
                .reduce(ZERO, BigDecimal::add);
    }

    public BigDecimal calcIncome(Target target) {
        return operationDAO.findIncomesBy(target)
                .stream()
                .map(op -> op.getAmount())
                .reduce(ZERO, BigDecimal::add);
    }
}
