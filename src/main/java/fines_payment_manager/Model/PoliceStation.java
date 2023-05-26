package fines_payment_manager.Model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "police_stations")
public class PoliceStation {

    @Id
    private int id;
    private String name;
    private String address;
    private String phone_number;
    public PoliceStation() {
    }

    public PoliceStation(int id, String name, String address, String phone_number) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "PoliceStation {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", address = '" + address + '\'' +
                ", phone_number = '" + phone_number + '\'' +
                '}';
    }
}
