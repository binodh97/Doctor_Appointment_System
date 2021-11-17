package view.tm;

public class AppointmentTM {
    private String pId;
    private String nameP;
    private String contactP;
    private String apId;
    private String dateA;
    private String timeA;
    private String id;
    private String nameD;
    private Double ChargeD;

    public AppointmentTM() {
    }

    public AppointmentTM(String pId, String nameP, String contactP, String apId, String dateA, String timeA, String id, String nameD, Double chargeD) {
        this.pId = pId;
        this.nameP = nameP;
        this.contactP = contactP;
        this.apId = apId;
        this.dateA = dateA;
        this.timeA = timeA;
        this.id = id;
        this.nameD = nameD;
        ChargeD = chargeD;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public String getContactP() {
        return contactP;
    }

    public void setContactP(String contactP) {
        this.contactP = contactP;
    }

    public String getApId() {
        return apId;
    }

    public void setApId(String apId) {
        this.apId = apId;
    }

    public String getDateA() {
        return dateA;
    }

    public void setDateA(String dateA) {
        this.dateA = dateA;
    }

    public String getTimeA() {
        return timeA;
    }

    public void setTimeA(String timeA) {
        this.timeA = timeA;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameD() {
        return nameD;
    }

    public void setNameD(String nameD) {
        this.nameD = nameD;
    }

    public Double getChargeD() {
        return ChargeD;
    }

    public void setChargeD(Double chargeD) {
        ChargeD = chargeD;
    }

    @Override
    public String toString() {
        return "AppointmentTM{" +
                "pId='" + pId + '\'' +
                ", nameP='" + nameP + '\'' +
                ", contactP='" + contactP + '\'' +
                ", apId='" + apId + '\'' +
                ", dateA='" + dateA + '\'' +
                ", timeA='" + timeA + '\'' +
                ", id='" + id + '\'' +
                ", nameD='" + nameD + '\'' +
                ", ChargeD=" + ChargeD +
                '}';
    }
}
