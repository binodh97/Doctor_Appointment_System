package model;

public class AppointmentDetails {
    private String apId;
    private String ptId;
    private String date;
    private Double charge;

    public AppointmentDetails() {
    }

    public AppointmentDetails(String apId, String ptId, String date, Double charge) {
        this.apId = apId;
        this.ptId = ptId;
        this.date = date;
        this.charge = charge;
    }

    public String getApId() {
        return apId;
    }

    public void setApId(String apId) {
        this.apId = apId;
    }

    public String getPtId() {
        return ptId;
    }

    public void setPtId(String ptId) {
        this.ptId = ptId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    @Override
    public String toString() {
        return "AppointmentDetails{" +
                "apId='" + apId + '\'' +
                ", ptId='" + ptId + '\'' +
                ", date='" + date + '\'' +
                ", charge=" + charge +
                '}';
    }
}
