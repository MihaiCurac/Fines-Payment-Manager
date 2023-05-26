package fines_payment_manager.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    private int id;
    private String name;
    private String address;
    private String driving_license;
    private String identity_card;

    public Driver() {
    }

    public Driver(int id, String name, String address, String driving_license, String identity_card) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.driving_license = driving_license;
        this.identity_card = identity_card;
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

    public String getDriving_license() {
        return driving_license;
    }

    public void setDriving_license(String driving_license) {
        this.driving_license = driving_license;
    }

    public String getIdentity_card() {
        return identity_card;
    }

    public void setIdentity_card(String identity_card) {
        this.identity_card = identity_card;
    }

    @Override
    public String toString() {
        return "Driver {" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", address = '" + address + '\'' +
                ", driving_license = '" + driving_license + '\'' +
                ", identity_card = '" + identity_card + '\'' +
                '}';
    }
}
