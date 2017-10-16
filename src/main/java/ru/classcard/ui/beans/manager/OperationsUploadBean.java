package ru.classcard.ui.beans.manager;

import org.jboss.logging.Logger;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import ru.classcard.services.operations.OperationsUploadService;
import ru.classcard.ui.beans.main.StudentClassBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import static java.util.ResourceBundle.getBundle;
import static javax.faces.context.FacesContext.getCurrentInstance;
import static ru.classcard.ui.locale.Messages.UTF8_CONTROL;

@ViewScoped
@ManagedBean(name = "operationsUpload")
public class OperationsUploadBean {

    private static final Logger LOGGER = Logger.getLogger(OperationsUploadBean.class);
    private static final String CARD_BUNDLE = "ru.classcard.ui.locale.card";

    @ManagedProperty(value = "#{classBean}")
    private StudentClassBean classBean;

    @ManagedProperty(value = "#{uploadService}")
    private OperationsUploadService uploadService;

    public void upload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if(file != null) {
            try {
                uploadService.uploadOperations(classBean.getCard(), file.getInputstream());
                addSuccessMessage(file);
            } catch (Exception ex) {
                LOGGER.error("Filename = " + file.getFileName(), ex);
                addErrorMessage();
            }
        }
    }

    private void addSuccessMessage(UploadedFile file) {
        FacesMessage message = new FacesMessage(getMessage("operations.uploaded"), file.getFileName());
        getCurrentInstance().addMessage(null, message);
    }

    private String getMessage(String key) {
        return getBundle(CARD_BUNDLE, getCurrentInstance().getViewRoot().getLocale(), UTF8_CONTROL).getString(key);
    }

    private void addErrorMessage() {
        FacesMessage message = new FacesMessage(getMessage("operations.upload.error"), getMessage("operations.upload.error.desc") );
        getCurrentInstance().addMessage(null, message);
    }

    public void setClassBean(StudentClassBean classBean) {
        this.classBean = classBean;
    }

    public void setUploadService(OperationsUploadService uploadService) {
        this.uploadService = uploadService;
    }

}
