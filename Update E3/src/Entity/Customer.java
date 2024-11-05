package Entity;

public class Customer {
    private int id;
    private String cusName;
    private String cusPhone;
    public Customer(){;}

    public Customer(int id, String cusName, String cusPhone) {
        this.id = id;
        this.cusName = cusName;
        this.cusPhone = cusPhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", cusName='" + cusName + '\'' +
                ", cusPhone='" + cusPhone + '\'' +
                '}';
    }
}
