package ru.classcard.ui.beans.admin;

import org.primefaces.model.LazyDataModel;
import ru.classcard.dao.StudentClassDAO;
import ru.classcard.model.StudentClass;
import ru.classcard.ui.datamodels.StudentClassLazyModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ViewScoped
@ManagedBean(name = "classes")
public class StudentClassListBean implements Serializable {

    private LazyDataModel<StudentClass> classList;

    @ManagedProperty(value = "#{classDAO}")
    private StudentClassDAO dao;

    public LazyDataModel<StudentClass> getList() {
        if (classList == null) {
            classList = new StudentClassLazyModel(dao);
        }
        return classList;
    }

    public void setDao(StudentClassDAO dao) {
        this.dao = dao;
    }
}
