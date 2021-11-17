package view.tm;

public class TreatmentvTM {
    private String tId;
    private String idP;
    private String nameP;
    private String treat;
    private String dateT;
    private String timeT;
    private Double cost;

    public TreatmentvTM() {
    }

    public TreatmentvTM(String tId, String idP, String nameP, String treat, String dateT, String timeT, Double cost) {
        this.tId = tId;
        this.idP = idP;
        this.nameP = nameP;
        this.treat = treat;
        this.dateT = dateT;
        this.timeT = timeT;
        this.cost = cost;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
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

    public String getTreat() {
        return treat;
    }

    public void setTreat(String treat) {
        this.treat = treat;
    }

    public String getDateT() {
        return dateT;
    }

    public void setDateT(String dateT) {
        this.dateT = dateT;
    }

    public String getTimeT() {
        return timeT;
    }

    public void setTimeT(String timeT) {
        this.timeT = timeT;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "TreatmentvTM{" +
                "tId='" + tId + '\'' +
                ", idP='" + idP + '\'' +
                ", nameP='" + nameP + '\'' +
                ", treat='" + treat + '\'' +
                ", dateT='" + dateT + '\'' +
                ", timeT='" + timeT + '\'' +
                ", cost=" + cost +
                '}';
    }
}
