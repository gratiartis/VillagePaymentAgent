package org.haftrust.verifier.view;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Login form bean validates that an email and password have been provided. It
 * *does not* validate whether these are valid formats, as it is not used for
 * persisting those properties.
 */
public class LoginFormBean {

    @NotEmpty
    private String email;
    
    // TODO Remove max when BCrypt hashing is in place.
    @NotEmpty
    private String password;

    public LoginFormBean() {
    }

    public LoginFormBean(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Details of the login form. Note that it does not include the password
     * text, as that would most likely lead to that value being logged.
     */
    @Override
    public String toString() {
        return "LoginFormBean: { email=" + email + " }";
    }

}
