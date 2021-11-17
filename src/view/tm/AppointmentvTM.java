package view.tm;

public class AppointmentvTM {

    private String apId;
    private String idP;
    private String nameP;
    private String idD;
    private String nameD;
    private String dateN;
    private String timeN;
    private String dateA;
    private String timeA;
    private Double ChargeD;

    public AppointmentvTM() {
    }

    public AppointmentvTM(String apId, String idP, String nameP, String idD, String nameD, String dateN, String timeN, String dateA, String timeA, Double chargeD) {
        this.apId = apId;
        this.idP = idP;
        this.nameP = nameP;
        this.idD = idD;
        this.nameD = nameD;
        this.dateN = dateN;
        this.timeN = timeN;
        this.dateA = dateA;
        this.timeA = timeA;
        ChargeD = chargeD;
    }

    public String getApId() {
        return apId;
    }

    public void setApId(String apId) {
        this.apId = apId;
    }

    public String getIdP() {
        return idP;
    }

    public void setIdP(String idP) {
        this.idP = idP;
    }

    public String getNameP() {
        return nameP;
    }

    public void setNameP(String nameP) {
        this.nameP = nameP;
    }

    public String getIdD() {
        return idD;
    }

    public void setIdD(String idD) {
        this.idD = idD;
    }

    public String getNameD() {
        return nameD;
    }

    public void setNameD(String nameD) {
        this.nameD = nameD;
    }

    public String getDateN() {
        return dateN;
    }

    public void setDateN(String dateN) {
        this.dateN = dateN;
    }

    public String getTimeN() {
        return timeN;
    }

    public void setTimeN(String timeN) {
        this.timeN = timeN;
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

    public Double getChargeD() {
        return ChargeD;
    }

    public void setChargeD(Double chargeD) {
        ChargeD = chargeD;
    }
}
