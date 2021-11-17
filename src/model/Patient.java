package model;

public class Patient {
    private String pId;
    private String pName;
    private String pAge;
    private String pGender;
    private String pAddress;
    private String pContact;
    private String pMail;

    public Patient() {
    }

    public Patient(String pId, String pName, String pAge, String pGender, String pAddress, String pContact, String pMail) {
        this.pId = pId;
        this.pName = pName;
        this.pAge = pAge;
        this.pGender = pGender;
        this.pAddress = pAddress;
        this.pContact = pContact;
        this.pMail = pMail;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpAge() {
        return pAge;
    }

    public void setpAge(String pAge) {
        this.pAge = pAge;
    }

    public String getpGender() {
        return pGender;
    }

    public void setpGender(String pGender) {
        this.pGender = pGender;
    }

    public String getpAddress() {
        return pAddress;
    }

    public void setpAddress(String pAddress) {
        this.pAddress = pAddress;
    }

    public String getpContact() {
        return pContact;
    }

    public void setpContact(String pContact) {
        this.pContact = pContact;
    }

    public String getpMail() {
        return pMail;
    }

    public void setpMail(String pMail) {
        this.pMail = pMail;
    }
}
