package ru.classcard.model;

import java.math.BigDecimal;

public class TargetIncome {

    private Student student;
    private BigDecimal amount;

    public TargetIncome(Student student, BigDecimal amount) {
        this.student = student;
        this.amount = amount;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
