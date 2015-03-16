/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.haftrust.verifier.validator;

import java.util.GregorianCalendar;
import org.haftrust.verifier.view.RegisterVerifierBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author be538
 */
public class IdentityDocumentValidator implements Validator {

    private static final Logger LOG = LoggerFactory.getLogger(IdentityDocumentValidator.class);

    @Override
    public boolean supports(Class clazz) {
        return clazz.equals(RegisterVerifierBean.class);
    }

    @Override
    public void validate(Object command, Errors errors) {
        RegisterVerifierBean rvBean = (RegisterVerifierBean) command;

        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "identityDocumentNumber", "required.identityDocumentNumber",
                "Number is required.");

        try {
            if (rvBean.getIdentityDocumentNumber().length() > 25) {
                errors.rejectValue("identityDocumentNumber", "required.identityDocumentNumber", "Number is required to be maximum 25 characters long.");
            }
        } catch (Exception e) {
            LOG.error("------------- identity document validator, number length", e);
        }

        boolean issueDateOK = true;
        boolean expiryDateOK = true;

        int year = 0;
        int month = 0;
        int day = 0;
        int expiryDateYear = 0;
        int expiryDateMonth = 0;
        int expiryDateDay = 0;

        try {
            String[] strSplit = rvBean.getIdentityDocumentIssueDate().split("-");
            String[] strExpiryDateSplit = rvBean.getIdentityDocumentExpiryDate().split("-");

            day = Integer.parseInt(strSplit[0]);
            month = Integer.parseInt(strSplit[1]);
            year = Integer.parseInt(strSplit[2]);
            expiryDateYear = Integer.parseInt(strExpiryDateSplit[2]);
            expiryDateMonth = Integer.parseInt(strExpiryDateSplit[1]);
            expiryDateDay = Integer.parseInt(strExpiryDateSplit[0]);
        } catch (/*NumberFormat*/Exception exc) {
            //errors.rejectValue("dob", "required.dob", "Date of Birth is invalid.");
        }

        GregorianCalendar gc = new GregorianCalendar();

        try {
            issueDateOK = true;

            gc.setLenient(false);        // must do this
            gc.set(GregorianCalendar.YEAR, year);
            gc.set(GregorianCalendar.MONTH, month - 1);// invalid month
            gc.set(GregorianCalendar.DATE, day);

            gc.getTime(); // exception thrown here
        } catch (Exception exception) {
            issueDateOK = false;
            errors.rejectValue("identityDocumentIssueDate", "required.identityDocumentIssueDate", "Issue Date is invalid.");
            LOG.warn("Issue date is invalid.", exception);
        }

        GregorianCalendar gcExpiryDate = new GregorianCalendar();

        try {
            expiryDateOK = true;

            gcExpiryDate.setLenient(false);        // must do this
            gcExpiryDate.set(GregorianCalendar.YEAR, expiryDateYear);
            gcExpiryDate.set(GregorianCalendar.MONTH, expiryDateMonth - 1);// invalid month
            gcExpiryDate.set(GregorianCalendar.DATE, expiryDateDay);

            gc.getTime(); // exception thrown here
        } catch (Exception exception) {
            expiryDateOK = false;
            errors.rejectValue("identityDocumentExpiryDate", "required.identityDocumentExpiryDate", "Expiry Date is invalid.");
            LOG.warn("Expiry date is invalid.", exception);
        }

        GregorianCalendar currentDate = new GregorianCalendar();
        java.util.Date d = currentDate.getTime();
        currentDate.setTime(d);

        if (issueDateOK) {
            try {
                if (currentDate.before(gc)) {
                    errors.rejectValue("identityDocumentIssueDate", "required.identityDocumentIssueDate", "The Issue Date has to be before the current date.");
                }
            } catch (Exception exception) {
                LOG.warn("The issue date has to be before the current date.", exception);
            }
        }

        if (expiryDateOK) {
            try {
                if (currentDate.after(gcExpiryDate)) {
                    errors.rejectValue("identityDocumentExpiryDate", "required.identityDocumentExpiryDate", "The Expiry Date has to be after the current date.");
                }
            } catch (Exception exception) {
                LOG.warn("The expiry date has to be after the current date.", exception);
            }
        }

        if (expiryDateOK && issueDateOK) {
            try {
                java.util.Date gcDate = gc.getTime();
                gcDate.setYear(gcDate.getYear() + 1);
                gc.setTime(gcDate);

                if (gc.after(gcExpiryDate)) {
                    errors.rejectValue("identityDocumentExpiryDate", "required.identityDocumentExpiryDate", "The Expiry Date has to be at least one year after the Issue Date.");
                }
            } catch (Exception exception) {
                LOG.warn("The expiry date has to be at least one year after the issue date.", exception);
            }
        }
    }
}
