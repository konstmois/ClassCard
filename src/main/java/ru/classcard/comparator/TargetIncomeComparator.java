package ru.classcard.comparator;

import ru.classcard.model.Student;
import ru.classcard.model.TargetIncome;

import java.util.Comparator;

import static java.math.BigDecimal.ZERO;

public class TargetIncomeComparator implements Comparator<TargetIncome> {

    @Override
    public int compare(TargetIncome t0, TargetIncome t1) {
        if (hasIncome(t0) && !hasIncome(t1)) {
            return -1;
        } else if (!hasIncome(t0) && hasIncome(t1)) {
            return 1;
        } else {
            return compareByNames(t0.getStudent(), t1.getStudent());
        }
    }

    private int compareByNames(Student s0, Student s1) {
        return s0.getLastName().compareTo(s1.getLastName());
    }

    private boolean hasIncome(TargetIncome t) {
        return t.getAmount() != null && t.getAmount().compareTo(ZERO) > 0;
    }
}
