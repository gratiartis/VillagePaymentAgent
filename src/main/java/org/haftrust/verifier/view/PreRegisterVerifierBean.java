package org.haftrust.verifier.view;

import javax.validation.constraints.Size;

import org.haftrust.verifier.validation.constraint.Password;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Miroslav
 */
public class PreRegisterVerifierBean {

    private int idVerifier;
    
    @NotEmpty @Size(max = 45) @Email
    private String email;
    
    // TODO Remove max when BCrypt hashing is in place.
    @NotEmpty @Password
    private String password;
    
    // TODO Remove max when BCrypt hashing is in place.
    @NotEmpty
    private String repassword;

    public int getIdVerifier() {
        return idVerifier;
    }

    public void setIdVerifier(int idVerifier) {
        this.idVerifier = idVerifier;
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

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    @Override
    public String toString() {
        return "PreRegisterVerifierBean{" + "idVerifier=" + idVerifier + ", email=" + email + ", password=" + password + ", repassword=" + repassword + '}';
    }
}
