package ru.classcard.ui.beans.manager;

import ru.classcard.dao.StudentDAO;
import ru.classcard.model.Student;
import ru.classcard.model.StudentClass;
import ru.classcard.ui.beans.CurrentUserBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "studentEdit")
public class StudentEditBean {

    @ManagedProperty(value = "#{currentUser}")
    private CurrentUserBean currentUserBean;

    @ManagedProperty(value = "#{studentDAO}")
    private StudentDAO dao;

    private Student student;

    public Student getStudent() {
        return student;
    }

    public void createNew() {
        student = new Student();
        student.setStudentClass(getCurrentClass());
    }

    public void save() {
        dao.save(student);
    }

    private StudentClass getCurrentClass() {
        return currentUserBean.getUser().getStudentClass();
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCurrentUserBean(CurrentUserBean currentUserBean) {
        this.currentUserBean = currentUserBean;
    }

    public void setDao(StudentDAO dao) {
        this.dao = dao;
    }


}
