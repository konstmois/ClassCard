package ru.classcard.model;

import ru.classcard.dao.UserDAO;

import static ru.classcard.model.UserRole.CLASS_MANAGER;
import static ru.classcard.model.UserRole.CLASS_MEMBER;

public class StudentClassContainer {

    private User classMember;
    private User classManager;
    private Card card;
    private StudentClass studentClass;

    private String prevClassManagerPass;
    private String prevClassMemberPass;


    public StudentClassContainer(StudentClass studentClass, UserDAO dao) {
        this.studentClass = studentClass;
        this.card = studentClass.getCard();
        this.classMember = dao.findUserBy(studentClass, CLASS_MEMBER);
        this.classManager = dao.findUserBy(studentClass, CLASS_MANAGER);

        prevClassManagerPass = classManager.getPassword();
        prevClassMemberPass = classMember.getPassword();
    }

    public StudentClassContainer() {
        studentClass = new StudentClass();
        card = new Card();
        classMember = new User();
        classManager = new User();

        studentClass.setCard(card);
        classManager.setStudentClass(studentClass);
        classManager.setRole(CLASS_MANAGER);
        classMember.setStudentClass(studentClass);
        classMember.setRole(CLASS_MEMBER);
    }

    public User getClassMember() {
        return classMember;
    }

    public User getClassManager() {
        return classManager;
    }

    public Card getCard() {
        return card;
    }

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public String getPrevClassManagerPass() {
        return prevClassManagerPass;
    }

    public String getPrevClassMemberPass() {
        return prevClassMemberPass;
    }
}
