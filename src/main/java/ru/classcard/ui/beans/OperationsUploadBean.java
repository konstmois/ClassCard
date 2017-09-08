package ru.classcard.ui.beans;

import org.jboss.logging.Logger;
import org.primefaces.model.UploadedFile;
import ru.classcard.services.operations.OperationsUploadService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ViewScoped
@ManagedBean(name = "operationsUpload")
public class OperationsUploadBean {

    private static final Logger LOGGER = Logger.getLogger(OperationsUploadBean.class);

    @ManagedProperty(value = "#{cardBean}")
    private CardBean cardBean;

    @ManagedProperty(value = "#{uploadService}")
    private OperationsUploadService uploadService;

    private UploadedFile file;

    public void upload() {
        if(file != null) {
            try {
                uploadService.uploadOperations(cardBean.getCard(), file.getInputstream());
                addSuccessMessage();
            } catch (Exception ex) {
                LOGGER.error("Filename = " + file.getFileName(), ex);
                addErrorMessage();
            }
        }
    }

    private void addSuccessMessage() {
        FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private void addErrorMessage() {
        FacesMessage message = new FacesMessage("Error", file.getFileName() + " is not uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void setCardBean(CardBean cardBean) {
        this.cardBean = cardBean;
    }

    public void setUploadService(OperationsUploadService uploadService) {
        this.uploadService = uploadService;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

}
