package view.tm;

public class AddTreatTM {
    private String id;
    private String name;
    private String age;
    private String contact;
    private String tId;
    private String treat;
    private String date;
    private String time;
    private Double fee;

    public AddTreatTM() {
    }

    public AddTreatTM(String id, String name, String age, String contact, String tId, String treat, String date, String time, Double fee) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.contact = contact;
        this.tId = tId;
        this.treat = treat;
        this.date = date;
        this.time = time;
        this.fee = fee;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getTreat() {
        return treat;
    }

    public void setTreat(String treat) {
        this.treat = treat;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "AddTreatTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", contact='" + contact + '\'' +
                ", tId='" + tId + '\'' +
                ", treat='" + treat + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", fee=" + fee +
                '}';
    }
}
