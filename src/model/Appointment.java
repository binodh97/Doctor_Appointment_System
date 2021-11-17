package model;

import java.util.ArrayList;

public class Appointment {
    private String aId;
    private String patId;
    private String patName;
    private String docId;
    private String docName;
    private String nDate;
    private String nTime;
    private String aDate;
    private String aTime;
    private Double cost;
    private ArrayList<AppointmentDetails> appointments;

    public Appointment() {
    }

    public Appointment(String aId, String patId, String patName, String docId, String docName, String nDate, String nTime, String aDate, String aTime, Double cost, ArrayList<AppointmentDetails> appointments) {
        this.aId = aId;
        this.patId = patId;
        this.patName = patName;
        this.docId = docId;
        this.docName = docName;
        this.nDate = nDate;
        this.nTime = nTime;
        this.aDate = aDate;
        this.aTime = aTime;
        this.cost = cost;
        this.appointments = appointments;
    }

    public Appointment(String aId, String patId, String patName, String docId, String docName, String nDate, String nTime, String aDate, String aTime, Double cost) {
        this.aId = aId;
        this.patId = patId;
        this.patName = patName;
        this.docId = docId;
        this.docName = docName;
        this.nDate = nDate;
        this.nTime = nTime;
        this.aDate = aDate;
        this.aTime = aTime;
        this.cost = cost;
    }

    public String getaId() {
        return aId;
    }

    public void setaId(String aId) {
        this.aId = aId;
    }

    public String getPatId() {
        return patId;
    }

    public void setPatId(String patId) {
        this.patId = patId;
    }

    public String getPatName() {
        return patName;
    }

    public void setPatName(String patName) {
        this.patName = patName;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getnDate() {
        return nDate;
    }

    public void setnDate(String nDate) {
        this.nDate = nDate;
    }

    public String getnTime() {
        return nTime;
    }

    public void setnTime(String nTime) {
        this.nTime = nTime;
    }

    public String getaDate() {
        return aDate;
    }

    public void setaDate(String aDate) {
        this.aDate = aDate;
    }

    public String getaTime() {
        return aTime;
    }

    public void setaTime(String aTime) {
        this.aTime = aTime;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public ArrayList<AppointmentDetails> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<AppointmentDetails> appointments) {
        this.appointments = appointments;
    }
}
