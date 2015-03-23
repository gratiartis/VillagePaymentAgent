package org.haftrust.verifier.view;

import javax.validation.constraints.NotNull;

/**
 * 
 */
public class SelectDistrictFormBean {

    @NotNull
    private Integer districtId;
    
    public SelectDistrictFormBean() {
    }

    public SelectDistrictFormBean(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    @Override
    public String toString() {
        return "SelectDistrictFormBean: { districtId=" + districtId + " }";
    }

}
