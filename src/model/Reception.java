package model;

public class Reception {
    private String rId;
    private String rName;
    private String rAge;
    private String rGender;
    private String rAddress;
    private String rContact;
    private String rMail;

    public Reception() {
    }

    public Reception(String rId, String rName, String rAge, String rGender, String rAddress, String rContact, String rMail) {
        this.rId = rId;
        this.rName = rName;
        this.rAge = rAge;
        this.rGender = rGender;
        this.rAddress = rAddress;
        this.rContact = rContact;
        this.rMail = rMail;
    }

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }

    public String getrAge() {
        return rAge;
    }

    public void setrAge(String rAge) {
        this.rAge = rAge;
    }

    public String getrGender() {
        return rGender;
    }

    public void setrGender(String rGender) {
        this.rGender = rGender;
    }

    public String getrAddress() {
        return rAddress;
    }

    public void setrAddress(String rAddress) {
        this.rAddress = rAddress;
    }

    public String getrContact() {
        return rContact;
    }

    public void setrContact(String rContact) {
        this.rContact = rContact;
    }

    public String getrMail() {
        return rMail;
    }

    public void setrMail(String rMail) {
        this.rMail = rMail;
    }

    @Override
    public String toString() {
        return "Reception{" +
                "rId='" + rId + '\'' +
                ", rName='" + rName + '\'' +
                ", rAge='" + rAge + '\'' +
                ", rGender='" + rGender + '\'' +
                ", rAddress='" + rAddress + '\'' +
                ", rContact='" + rContact + '\'' +
                ", rMail='" + rMail + '\'' +
                '}';
    }
}
