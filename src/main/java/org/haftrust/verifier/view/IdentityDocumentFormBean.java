package org.haftrust.verifier.view;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.haftrust.verifier.model.IdentityDocument;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 */
public class IdentityDocumentFormBean {

    @NotNull
    private String documentType;
    
    @NotEmpty @Size(max = 25)
    private String number;
    
    @Past
    private String issueDate;
    
    @Future
    private String expiryDate;
    
    public IdentityDocumentFormBean() {
    }

    public IdentityDocumentFormBean(IdentityDocument doc) {
        if (doc == null) return;
        
        this.documentType = doc.getType().getValue();
        this.number = doc.getNumber();
        this.issueDate = doc.getIssueDate().toString();
        this.expiryDate = doc.getExpiryDate().toString();
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "ReferenceFormBean: { " + "documentType=" + documentType
                + ", number=" + number + ", issueDate=" + issueDate
                + ", expiryDate=" + expiryDate + " }";
    }

}
