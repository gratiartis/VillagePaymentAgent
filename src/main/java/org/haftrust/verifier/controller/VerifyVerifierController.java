/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.haftrust.verifier.controller;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.haftrust.verifier.model.Address;
import org.haftrust.verifier.model.Bank;
import org.haftrust.verifier.model.District;
import org.haftrust.verifier.model.Fom;
import org.haftrust.verifier.model.IdentityDocument;
//import org.haftrust.verifier.model.Image;
import org.haftrust.verifier.model.Interview;
import org.haftrust.verifier.model.Reference;
import org.haftrust.verifier.model.StaticData;
import org.haftrust.verifier.model.Verifier;
import org.haftrust.verifier.service.VerifierService;
import org.haftrust.verifier.view.VerifyVerifierBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;

/**
 *
 * @author Miroslav
 */
public class VerifyVerifierController extends AbstractWizardFormController {

    private static final Logger LOG = LoggerFactory.getLogger(VerifyVerifierController.class);

    private VerifierService verifierService;
    private String cancelView;
    private String successView;
    private String saveView;
    private String deleteView;

    private List<StaticData> verificationStatusList = new ArrayList<StaticData>();

    public String getDeleteView() {
        return deleteView;
    }

    public void setDeleteView(String deleteView) {
        this.deleteView = deleteView;
    }

    public String getSaveView() {
        return saveView;
    }

    public void setSaveView(String saveView) {
        this.saveView = saveView;
    }

    public String getSuccessView() {
        return successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }

    public String getCancelView() {
        return cancelView;
    }

    public void setCancelView(String cancelView) {
        this.cancelView = cancelView;
    }

    public VerifierService getVerifierService() {
        return verifierService;
    }

    public void setVerifierService(VerifierService verifierService) {
        this.verifierService = verifierService;
    }

    protected Map referenceData(HttpServletRequest request, Object command, Errors errors, int page) throws Exception {
        Map<Object, Object> dataMap = new HashMap<Object, Object>();
        VerifyVerifierBean vvBean = (VerifyVerifierBean) command;

        if (page == 0) {
            dataMap.put("countryList", this.verifierService.getCountryList());
            LOG.debug("------------------------ controller verify verifier reference data country list");
        }

        if (page == 1) {
            dataMap.put("regionList", this.verifierService.getRegionList());
            LOG.debug("------------------------ controller verify verifier reference data region list");
        }

        if (page == 3) {
            this.verificationStatusList = this.verifierService.getVerificationStatusList();
            dataMap.put("verificationStatusList", this.verificationStatusList);
        }

        return dataMap;
    }

    protected void validatePage(Object command, Errors errors, int page) {
        VerifyVerifierBean vvBean = (VerifyVerifierBean) command;

        LOG.debug("----------------------- validate page method");
        if (page == 5) {
            LOG.debug("----------------------- validate page login country{}", getValidators()[0].getClass());
            //getValidators()[0].validate(command, errors);

            /*if(vvBean.getVerifierVerificationComment().equals(""))
             {
             errors.rejectValue("verifierVerificationComment", "required.verifierVerificationComment", "Verification Comment is required.");
             }*/
        }
    }

    protected void postProcessPage(HttpServletRequest request, Object command, Errors errors, int page) throws Exception {
        VerifyVerifierBean vvBean = (VerifyVerifierBean) command;

        if (page == 0 && request.getParameter("_target1") != null) {
            vvBean.setCountry(this.verifierService.setVerifierCountry(vvBean.getIdCountry()));
        }

        if (page == 1 && request.getParameter("_target2") != null) {
            LOG.debug("------------------------- post process page search verifier");
            vvBean.setRegion(this.verifierService.setVerifierRegion(vvBean.getIdRegion()));

            List<Verifier> v = new ArrayList<Verifier>();
            //vvBean.setVerifierList(this.verifierService.getRegisteredVerifiers());
            v = this.verifierService.getRegisteredVerifiers();
            LOG.debug("------------------------- post process page search verifier, v list size: {}", v.size());
            vvBean.setVerifierList(v);
            vvBean.setvBean(v);
        }

        if (page == 2 && request.getParameter("_target3") != null) {
            this.verifierService.getRegisteredVerifierDetails(vvBean.getIdVerifier());

            Verifier verifier = new Verifier();
            verifier = this.verifierService.getVerifier();
            vvBean.setFirstName(verifier.getFirstName());
            vvBean.setMiddleName(verifier.getMiddleName());
            vvBean.setLastName(verifier.getLastName());
            vvBean.setGender(verifier.getGender().getValue().toUpperCase());
            if (verifier.getDob() != null) {
                String date;
                date = verifier.getDob().toString();
                int year = 0;
                int month = 0;
                int day = 0;
                String[] strSplit = date.split("-");
                day = Integer.parseInt(strSplit[2]);
                month = Integer.parseInt(strSplit[1]);
                year = Integer.parseInt(strSplit[0]);
                date = Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year);

                vvBean.setDob(date);
            }
            vvBean.setEmail(verifier.getEmail());
            LOG.debug("--------------------verify verifier controller post process page verifier email: {}", vvBean.getEmail());
            vvBean.setTelephoneNumber(verifier.getTelephoneNumber());
            vvBean.setEducationType(verifier.getEducationType() == null ? null : verifier.getEducationType().value.toUpperCase());
            vvBean.setEducationLevel(verifier.getEducationLevel() == null ? null : verifier.getEducationLevel().value.toUpperCase());
            vvBean.setVerifierVerificationStatus(verifier.getVerificationStatus() == null ? null : verifier.getVerificationStatus().value);
            vvBean.setVerifierVerificationComment(verifier.getVerificationComment());
            vvBean.setIdVerifier(verifier.getId());

            Address address = new Address();
            address = this.verifierService.getAddress();
            vvBean.setAddress(address);
            vvBean.setStreet(address.getStreet());
            vvBean.setVillage(address.getVillage());
            vvBean.setPostcode(address.getPostcode());
            vvBean.setTown(address.getTown());
            vvBean.setCity(address.getCity());

            District district = new District();
            district = this.verifierService.getVerifierDistrict();
            LOG.debug("--------------------------------- district: {}", district);
            vvBean.setDistrict(district.getDescription());
            vvBean.setAddressRegion(this.verifierService.getVerifierRegion().getDescription());
            vvBean.setAddressCountry(this.verifierService.getVerifierCountry().getDescription());
            vvBean.setAddressVerificationStatus(verifier.getVerificationStatus() == null ? null : address.getVerificationStatus().value);
            vvBean.setAddressVerificationComment(address.getVerificationComment());

            org.haftrust.verifier.model.Image image = new org.haftrust.verifier.model.Image();

            image = this.verifierService.getImage();

            /*for(int i=0; i<image.getPhoto().length; i++)
             {
             LOG.debug("=================== photo byte: " + image.getPhoto()[i]);
             }*/
            vvBean.setFile(image.getPhoto());
            vvBean.setImage(image);

            /*InputStream in = new ByteArrayInputStream(image.getPhoto());
             //OutputStream out = new ByteArrayOutputStream();
             BufferedImage bi = ImageIO.read(in);
             java.awt.Image i = bi.getScaledInstance(bi.getWidth(), bi.getHeight(), bi.TYPE_INT_RGB);
             //RenderedImage ri
             //ImageIO.write(bi, "jpg",new File("c:\\snap.jpg"));
             //ImageIO.
             LOG.debug("================== bi: " + bi);
             LOG.debug("================== bi type : " + bi.getType());
             LOG.debug("================== i: " + i);
             vvBean.setFileBi(bi);
             // Create an image to save
             RenderedImage rendImage = myCreateImage(image.getPhoto());

             // Write generated image to a file
             try {
             // Save as PNG
             File file = new File("C:\\newimage.png");
             ImageIO.write(rendImage, "png", file);

             // Save as JPEG
             file = new File("C:\\newimage.jpg");
             ImageIO.write(rendImage, "jpg", file);
             } catch (IOException e) {
             }*/
             //try{

            /*BufferedImage originalImage =
             ImageIO.read(new File("c:\\image\\mypic.jpg"));

             //convert BufferedImage to byte array
             ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ImageIO.write( originalImage, "jpg", baos );
             baos.flush();
             byte[] imageInByte = baos.toByteArray();
             baos.close();*/
                    //convert byte array back to BufferedImage
            //InputStream in = new ByteArrayInputStream(image.getPhoto());
            //BufferedImage bImageFromConvert = ImageIO.read(in);
                    //ImageIO.write(bImageFromConvert, "jpg",
            //    new File("c:\\snap.jpg"));
              // }catch(IOException e){
            //     LOG.debug(e.getMessage());
            // }
            //BufferedImage imag=ImageIO.read(new ByteArrayInputStream(image.getPhoto()));
            //ImageIO.write(imag, "jpg", new File("images/","snap.jpg"));

            /*try {

             // display the image
             response.setContentType("image/gif");

             OutputStream o = response.getOutputStream();

             o.write(((VerifyVerifierBean) request.getAttribute("vvBean")).getFile());

             o.flush();

             o.close();

             } catch (Exception e) {

             out.println("Unable To Display image");

             out.println("Image Display Error=" + e.getMessage());

             return;

             } */

            /*ByteArrayInputStream bis = new ByteArrayInputStream(image.getPhoto());
             Iterator<?> readers = ImageIO.getImageReadersByFormatName("jpg");
             //ImageIO is a class containing static convenience methods for locating ImageReaders
             //and ImageWriters, and performing simple encoding and decoding.

             ImageReader reader = (ImageReader) readers.next();
             Object source = bis; // File or InputStream, it seems file is OK

             ImageInputStream iis = ImageIO.createImageInputStream(source);
             //Returns an ImageInputStream that will take its input from the given Object

             reader.setInput(iis, true);
             ImageReadParam param = reader.getDefaultReadParam();

             Image iimage = reader.read(0, param);
             //got an image file

             BufferedImage bufferedImage = new BufferedImage(iimage.getWidth(null), iimage.getHeight(null), BufferedImage.TYPE_INT_RGB);
             //bufferedImage is the RenderedImage to be written
             Graphics2D g2 = bufferedImage.createGraphics();
             g2.drawImage(iimage, null, null);
             File imageFile = new File("C:\\snap.jpg");
             ImageIO.write(bufferedImage, "jpg", imageFile);
             //"jpg" is the format of the image
             //imageFile is the file to be written to.

             LOG.debug(imageFile.getPath());*/
            vvBean.setFileVerificationStatus(image.getVerificationStatus() == null ? null : image.getVerificationStatus().value);
            vvBean.setFileVerificationComment(image.getVerificationComment());

            Bank bank = new Bank();
            bank = this.verifierService.getBank();
            LOG.debug("------------ verifier controller bank: {}", bank);
            vvBean.setBankAccountNumber(bank.getAccountNumber());
            vvBean.setBankName(bank.getBankName());
            vvBean.setBankContactNumber(bank.getContactNumber());
            vvBean.setBankAddress(bank.getAddress());
            vvBean.setBankSortCode(bank.getSortcode());
            vvBean.setBankIban(bank.getIban());
            vvBean.setBankVerificationStatus(bank.getVerificationStatus() == null ? null : bank.getVerificationStatus().value);
            vvBean.setBankVerificationComment(bank.getVerificationComment());

            Reference reference1 = new Reference();
            reference1 = this.verifierService.getReference1();
            vvBean.setReference1Title(reference1.getTitle().toUpperCase());
            vvBean.setReference1FullName(reference1.getFullName());
            vvBean.setReference1OrganisationName(reference1.getOrganisationName());
            vvBean.setReference1Designation(reference1.getDesignation());
            vvBean.setReference1ContactNumber(reference1.getContactNumber());
            vvBean.setReference1Email(reference1.getEmail());
            vvBean.setReference1Address(reference1.getAddress());
            vvBean.setReference1VerificationStatus(reference1.getVerificationStatus() == null ? null : reference1.getVerificationStatus().value);
            vvBean.setReference1VerificationComment(reference1.getVerificationComment());

            Reference reference2 = new Reference();
            reference2 = this.verifierService.getReference2();
            vvBean.setReference2Title(reference2.getTitle().toUpperCase());
            vvBean.setReference2FullName(reference2.getFullName());
            vvBean.setReference2OrganisationName(reference2.getOrganisationName());
            vvBean.setReference2Designation(reference2.getDesignation());
            vvBean.setReference2ContactNumber(reference2.getContactNumber());
            vvBean.setReference2Email(reference2.getEmail());
            vvBean.setReference2Address(reference2.getAddress());
            vvBean.setReference2VerificationStatus(reference2.getVerificationStatus() == null ? null : reference2.getVerificationStatus().value);
            vvBean.setReference2VerificationComment(reference2.getVerificationComment());

            IdentityDocument id = new IdentityDocument();
            id = this.verifierService.getIdentityDocument();
            vvBean.setIdentityDocumentType(id.getType() == null ? null : id.getType().value.toUpperCase());
            vvBean.setIdentityDocumentNumber(id.getNumber());
            if (id.getIssueDate() != null) {
                String date;
                date = id.getIssueDate().toString();
                int year = 0;
                int month = 0;
                int day = 0;
                String[] strSplit = date.split("-");
                day = Integer.parseInt(strSplit[2]);
                month = Integer.parseInt(strSplit[1]);
                year = Integer.parseInt(strSplit[0]);
                date = Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year);

                vvBean.setIdentityDocumentIssueDate(date);
            }
            if (id.getExpiryDate() != null) {
                String date;
                date = id.getExpiryDate().toString();
                int year = 0;
                int month = 0;
                int day = 0;
                String[] strSplit = date.split("-");
                day = Integer.parseInt(strSplit[2]);
                month = Integer.parseInt(strSplit[1]);
                year = Integer.parseInt(strSplit[0]);
                date = Integer.toString(day) + "-" + Integer.toString(month) + "-" + Integer.toString(year);

                vvBean.setIdentityDocumentExpiryDate(date);
            }

            vvBean.setIdentityDocumentVerificationStatus(id.getVerificationStatus() == null ? null : id.getVerificationStatus().value);
            vvBean.setIdentityDocumentVerificationComment(id.getVerificationComment());

            List<Fom> fom = new ArrayList<Fom>();
            fom = this.verifierService.getFoms();
            vvBean.setFomList(fom);
            vvBean.setfBean(fom);
        }

        // cancel allocate device select country page
        if (page == 0 && request.getParameter("_target4") != null) {
            vvBean.setTarget("_target0");
        }

        // cancel registration select region page
        if (page == 1 && request.getParameter("_target4") != null) {
            vvBean.setTarget("_target1");
        }

        // cancel registration select district page
        if (page == 2 && request.getParameter("_target4") != null) {
            vvBean.setTarget("_target2");
        }

        // cancel registration select district page
        if (page == 3 && request.getParameter("_target4") != null) {
            vvBean.setTarget("_target3");
        }
    }

    // Returns a generated image.
    /*public RenderedImage myCreateImage(byte b[]) {
     int width = 100;
     int height = 100;

     // Create a buffered image in which to draw
     BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

     //InputStream in = new ByteArrayInputStream(b);
     //InputStream in = new ByteArrayInputStream(image.getPhoto());
     //OutputStream out = new ByteArrayOutputStream();
     try{
     //BufferedImage bi = ImageIO.read(in);
     }catch(Exception ex)
     {

     }

     //java.awt.Image i = bi.getScaledInstance(bi.getWidth(), bi.getHeight(), bi.TYPE_INT_RGB);
     // Create a graphics contents on the buffered image
     Graphics2D g2d = bufferedImage.createGraphics();

     // Draw graphics
     g2d.setColor(Color.white);
     g2d.fillRect(0, 0, width, height);
     g2d.setColor(Color.black);
     g2d.fillOval(0, 0, width, height);

     // Graphics context no longer needed so dispose it
     g2d.dispose();

     return bufferedImage;
     }*/
    protected ModelAndView processCancel(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        VerifyVerifierBean vvBean = (VerifyVerifierBean) command;
        vvBean = new VerifyVerifierBean();

        return new ModelAndView(this.getCancelView(), "vvBean", vvBean);
    }

    protected ModelAndView processFinish(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        VerifyVerifierBean vvBean = (VerifyVerifierBean) command;

        Fom fom = new Fom();

        List<String> sl = new ArrayList<String>();
        boolean verified = true;
        StaticData sdVerified = new StaticData();

        if (request.getParameter("_finish").equals("Confirm")) {
            this.verifierService.failVerification(vvBean.getVerifierVerificationComment());
            LOG.debug("---------------------- verify verifier controller, delete verification");

            return new ModelAndView(this.getDeleteView(), "vvBean", vvBean);
        }

        sl.add(vvBean.getVerifierVerificationStatus());
        sl.add(vvBean.getAddressVerificationStatus());
        sl.add(vvBean.getFileVerificationStatus());
        sl.add(vvBean.getIdentityDocumentVerificationStatus());
        sl.add(vvBean.getBankVerificationStatus());
        sl.add(vvBean.getReference1VerificationStatus());
        sl.add(vvBean.getReference2VerificationStatus());

        for (int i = 0; i < this.verificationStatusList.size(); i++) {
            if (this.verificationStatusList.get(i).getDescription().equals("Verified")) {
                sdVerified = this.verificationStatusList.get(i);
            }
        }

        for (int i = 0; i < sl.size(); i++) {
            if (!sl.get(i).equals(sdVerified.getValue())) {
                verified = false;
            }
        }

        if (verified) {
            fom = this.verifierService.setVerifyVerifierDetils(vvBean.getVerifierVerificationStatus(),
                    vvBean.getVerifierVerificationComment(),
                    vvBean.getAddressVerificationStatus(),
                    vvBean.getAddressVerificationComment(),
                    vvBean.getFileVerificationStatus(),
                    vvBean.getFileVerificationComment(),
                    vvBean.getIdentityDocumentVerificationStatus(),
                    vvBean.getIdentityDocumentVerificationComment(),
                    vvBean.getBankVerificationStatus(),
                    vvBean.getBankVerificationComment(),
                    vvBean.getReference1VerificationStatus(),
                    vvBean.getReference1VerificationComment(),
                    vvBean.getReference2VerificationStatus(),
                    vvBean.getReference2VerificationComment(),
                    vvBean.getIdFom(),
                    verified);

            vvBean.setFiledOperativeManager(fom);

            Interview i = new Interview();

            i = this.verifierService.verifyVerifier();

            vvBean.setInterview(i);
            LOG.debug("---------------------- verify verifier controller, verification");
            return new ModelAndView(this.getSuccessView(), "vvBean", vvBean);
        } else {
            this.verifierService.setVerifyVerifierDetils(vvBean.getVerifierVerificationStatus(),
                    vvBean.getVerifierVerificationComment(),
                    vvBean.getAddressVerificationStatus(),
                    vvBean.getAddressVerificationComment(),
                    vvBean.getFileVerificationStatus(),
                    vvBean.getFileVerificationComment(),
                    vvBean.getIdentityDocumentVerificationStatus(),
                    vvBean.getIdentityDocumentVerificationComment(),
                    vvBean.getBankVerificationStatus(),
                    vvBean.getBankVerificationComment(),
                    vvBean.getReference1VerificationStatus(),
                    vvBean.getReference1VerificationComment(),
                    vvBean.getReference2VerificationStatus(),
                    vvBean.getReference2VerificationComment(),
                    vvBean.getIdFom(),
                    verified);

            this.verifierService.saveVerifyVerifier();

            LOG.debug("---------------------- verify verifier controller, save");
            return new ModelAndView(this.getSaveView(), "vvBean", vvBean);
        }
    }
}
