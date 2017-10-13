package ru.classcard.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Target {

    private Long id;
    private String name;
    private BigDecimal requiredAmount;
    private StudentClass studentClass;
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    public BigDecimal getRequiredAmount() {
        return requiredAmount;
    }

    public void setRequiredAmount(BigDecimal requiredAmount) {
        this.requiredAmount = requiredAmount;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Target)) {
            return false;
        }
        Target t = (Target) o;
        return Objects.equals(getId(), t.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
