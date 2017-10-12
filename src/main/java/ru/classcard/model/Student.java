package ru.classcard.model;

import java.util.Objects;

public class Student {

    private Long id;
    private String name;
    private String lastName;
    private StudentClass studentClass;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student s = (Student) o;
        return Objects.equals(getId(), s.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
