package ru.classcard.model;

import ru.classcard.dao.UserDAO;

import static ru.classcard.model.UserRole.CLASS_ADMIN;
import static ru.classcard.model.UserRole.CLASS_MEMBER;

public class StudentClassContainer {

    private User classMember;
    private User classAdmin;
    private Card card;
    private StudentClass studentClass;

    private String prevClassAdminPass;
    private String prevClassMemberPass;


    public StudentClassContainer(StudentClass studentClass, UserDAO dao) {
        this.studentClass = studentClass;
        this.card = studentClass.getCard();
        this.classMember = dao.findUserBy(studentClass, CLASS_MEMBER);
        this.classAdmin = dao.findUserBy(studentClass, CLASS_ADMIN);

        prevClassAdminPass = classAdmin.getPassword();
        prevClassMemberPass = classMember.getPassword();
    }

    public StudentClassContainer() {
        studentClass = new StudentClass();
        card = new Card();
        classMember = new User();
        classAdmin = new User();

        studentClass.setCard(card);
        classAdmin.setStudentClass(studentClass);
        classAdmin.setRole(CLASS_ADMIN);
        classMember.setStudentClass(studentClass);
        classMember.setRole(CLASS_MEMBER);
    }

    public User getClassMember() {
        return classMember;
    }

    public User getClassAdmin() {
        return classAdmin;
    }

    public Card getCard() {
        return card;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public String getPrevClassAdminPass() {
        return prevClassAdminPass;
    }

    public String getPrevClassMemberPass() {
        return prevClassMemberPass;
    }
}
