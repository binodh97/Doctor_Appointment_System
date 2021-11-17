package model;

public class Doctor {
    private String dId;
    private String dName;
    private String dAge;
    private String dGender;
    private String dAddress;
    private String dContact;
    private String dSpecialize;
    private Double dFees;

    public Doctor() {
    }

    public Doctor(String dId, String dName, String dAge, String dGender, String dAddress, String dContact, String dSpecialize, Double dFees) {
        this.dId = dId;
        this.dName = dName;
        this.dAge = dAge;
        this.dGender = dGender;
        this.dAddress = dAddress;
        this.dContact = dContact;
        this.dSpecialize = dSpecialize;
        this.dFees = dFees;
    }

    public String getdId() {
        return dId;
    }

    public void setdId(String dId) {
        this.dId = dId;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getdAge() {
        return dAge;
    }

    public void setdAge(String dAge) {
        this.dAge = dAge;
    }

    public String getdGender() {
        return dGender;
    }

    public void setdGender(String dGender) {
        this.dGender = dGender;
    }

    public String getdAddress() {
        return dAddress;
    }

    public void setdAddress(String dAddress) {
        this.dAddress = dAddress;
    }

    public String getdContact() {
        return dContact;
    }

    public void setdContact(String dContact) {
        this.dContact = dContact;
    }

    public String getdSpecialize() {
        return dSpecialize;
    }

    public void setdSpecialize(String dSpecialize) {
        this.dSpecialize = dSpecialize;
    }

    public Double getdFees() {
        return dFees;
    }

    public void setdFees(Double dFees) {
        this.dFees = dFees;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "dId='" + dId + '\'' +
                ", dName='" + dName + '\'' +
                ", dAge='" + dAge + '\'' +
                ", dGender='" + dGender + '\'' +
                ", dAddress='" + dAddress + '\'' +
                ", dContact='" + dContact + '\'' +
                ", dSpecialize='" + dSpecialize + '\'' +
                ", dFees=" + dFees +
                '}';
    }
}
