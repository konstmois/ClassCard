package ru.classcard.comparator;

import ru.classcard.model.Student;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student s0, Student s1) {
        return s0.getLastName().compareTo(s1.getLastName());
    }

}
