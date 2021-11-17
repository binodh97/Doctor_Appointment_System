package model;

public class Treatment {
    private String tId;
    private String tTreat;
    private Double tFee;

    public Treatment(String tId, String tTreat, Double tFee) {
        this.tId = tId;
        this.tTreat = tTreat;
        this.tFee = tFee;
    }

    public Treatment() {
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String gettTreat() {
        return tTreat;
    }

    public void settTreat(String tTreat) {
        this.tTreat = tTreat;
    }

    public Double gettFee() {
        return tFee;
    }

    public void settFee(Double tFee) {
        this.tFee = tFee;
    }

    @Override
    public String toString() {
        return "Treatment{" +
                "tId='" + tId + '\'' +
                ", tTreat='" + tTreat + '\'' +
                ", tFee=" + tFee +
                '}';
    }
}
