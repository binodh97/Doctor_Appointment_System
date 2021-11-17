package view.tm;

public class TreatmentTM {
    private String id;
    private String treat;
    private Double fee;

    public TreatmentTM() {
    }

    public TreatmentTM(String id, String treat, Double fee) {
        this.id = id;
        this.treat = treat;
        this.fee = fee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTreat() {
        return treat;
    }

    public void setTreat(String treat) {
        this.treat = treat;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "TreatmentTM{" +
                "id='" + id + '\'' +
                ", treat='" + treat + '\'' +
                ", fee=" + fee +
                '}';
    }
}
