package org.haftrust.verifier.view;

import javax.validation.constraints.NotNull;

/**
 * 
 */
public class SelectRegionFormBean {

    @NotNull
    private Integer regionId;
    
    public SelectRegionFormBean() {
    }

    public SelectRegionFormBean(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    @Override
    public String toString() {
        return "SelectRegionFormBean: { regionId=" + regionId + " }";
    }

}
