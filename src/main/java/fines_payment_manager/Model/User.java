package fines_payment_manager.Model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    private int id;
    private String email;
    private String password;
    private String role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "police_station_id", referencedColumnName = "id")
    private PoliceStation policeStation;
    public User() {
    }

    public User(int id, String email, String password, String role, PoliceStation policeStation) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.policeStation = policeStation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public PoliceStation getPoliceStation() {
        return policeStation;
    }

    public void setPoliceStation(PoliceStation policeStation) {
        this.policeStation = policeStation;
    }

    @Override
    public String toString() {
        return "User {" +
                "id = " + id +
                ", email = '" + email + '\'' +
                ", password = '" + password + '\'' +
                ", role = '" + role + '\'' +
                ", policeStation = " + policeStation +
                '}';
    }
}
