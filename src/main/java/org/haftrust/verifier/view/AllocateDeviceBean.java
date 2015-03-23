/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.haftrust.verifier.view;

import java.util.ArrayList;
import java.util.List;

import org.haftrust.verifier.model.Country;
import org.haftrust.verifier.model.Device;
import org.haftrust.verifier.model.Region;
import org.haftrust.verifier.model.Verifier;
import org.hibernate.validator.constraints.Email;

/**
 *
 * @author Miroslav
 */
public class AllocateDeviceBean {

    private String target;

    private int idCountry;
    private Country country;
    private int idRegion;
    private Region region;
    private int idVerifier;
    private List<Verifier> verifierList = new ArrayList<>();
    private List<VBean> vBean = new ArrayList<>();

    private String firstName;
    private String middleName;
    private String lastName;
    private String dob;
    @Email
    private String email;

    private long imei;
    private Device device;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public int getIdVerifier() {
        return idVerifier;
    }

    public void setIdVerifier(int idVerifier) {
        this.idVerifier = idVerifier;
    }

    public List<Verifier> getVerifierList() {
        return verifierList;
    }

    public void setVerifierList(List<Verifier> verifierList) {
        this.verifierList = verifierList;
    }

    public List<VBean> getvBean() {
        return vBean;
    }

    public void setvBean(List<Verifier> v) {
        //this.vBean.clear();
        List<VBean> vbl = new ArrayList<VBean>();
        for (int i = 0; i < v.size(); i++) {
            String s;

            String date;
            date = v.get(i).getDob().toString();
            int year = 0;
            int month = 0;
            int day = 0;
            String[] strSplit = date.split("-");
            day = Integer.parseInt(strSplit[2]);
            month = Integer.parseInt(strSplit[1]);
            year = Integer.parseInt(strSplit[0]);
            date = Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year);

            s = v.get(i).getFirstName() + " " + v.get(i).getLastName() + " " + date;
            VBean vb = new VBean();
            vb.setVer(v.get(i));
            vb.setLabel(s);
            vb.setId(v.get(i).getId());
            vbl.add(vb);
        }

        this.vBean = vbl;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getIdCountry() {
        return idCountry;
    }

    public void setIdCountry(int idCountry) {
        this.idCountry = idCountry;
    }

    static class VBean {

        private Verifier ver = new Verifier();
        private String label;
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public Verifier getVer() {
            return ver;
        }

        public void setVer(Verifier ver) {
            this.ver = ver;
        }
    }

    @Override
    public String toString() {
        return "AllocateDeviceBean{" + "target=" + target + ", idCountry=" + idCountry + ", country=" + country + ", idRegion=" + idRegion + ", region=" + region + ", idVerifier=" + idVerifier + ", verifierList=" + verifierList + ", vBean=" + vBean + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", dob=" + dob + ", email=" + email + ", imei=" + imei + ", device=" + device + '}';
    }
    
}
