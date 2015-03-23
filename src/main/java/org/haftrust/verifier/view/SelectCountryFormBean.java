package org.haftrust.verifier.view;

import javax.validation.constraints.NotNull;

/**
 * 
 */
public class SelectCountryFormBean {

    @NotNull
    private Integer countryId;
    
    public SelectCountryFormBean() {
    }

    public SelectCountryFormBean(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    @Override
    public String toString() {
        return "SelectCountryFormBean: { countryId=" + countryId + " }";
    }

}
