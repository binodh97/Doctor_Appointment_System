package model;

public class Nurse {
    private String nId;
    private String nName;
    private String nAge;
    private String nGender;
    private String nAddress;
    private String nContact;

    public Nurse() {
    }

    public Nurse(String nId, String nName, String nAge, String nGender, String nAddress, String nContact) {
        this.nId = nId;
        this.nName = nName;
        this.nAge = nAge;
        this.nGender = nGender;
        this.nAddress = nAddress;
        this.nContact = nContact;
    }

    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }

    public String getnAge() {
        return nAge;
    }

    public void setnAge(String nAge) {
        this.nAge = nAge;
    }

    public String getnGender() {
        return nGender;
    }

    public void setnGender(String nGender) {
        this.nGender = nGender;
    }

    public String getnAddress() {
        return nAddress;
    }

    public void setnAddress(String nAddress) {
        this.nAddress = nAddress;
    }

    public String getnContact() {
        return nContact;
    }

    public void setnContact(String nContact) {
        this.nContact = nContact;
    }

    @Override
    public String toString() {
        return "Nurse{" +
                "nId='" + nId + '\'' +
                ", nName='" + nName + '\'' +
                ", nAge='" + nAge + '\'' +
                ", nGender='" + nGender + '\'' +
                ", nAddress='" + nAddress + '\'' +
                ", nContact='" + nContact + '\'' +
                '}';
    }
}
