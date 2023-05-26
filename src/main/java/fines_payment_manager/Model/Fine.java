package fines_payment_manager.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "fines")
public class Fine {

    @Id
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Driver driver;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "police_station_id", referencedColumnName = "id")
    private PoliceStation police_station;
    private String type;
    private Double amount;
    private String status;

    public Fine() {
    }

    public Fine(int id, Driver driver, PoliceStation police_station, String type, Double amount, String status) {
        this.id = id;
        this.driver = driver;
        this.police_station = police_station;
        this.type = type;
        this.amount = amount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public PoliceStation getPolice_station() {
        return police_station;
    }

    public void setPolice_station(PoliceStation police_station) {
        this.police_station = police_station;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Fine {" +
                "id = " + id +
                ", driver = " + driver +
                ", police_station = " + police_station +
                ", type = '" + type + '\'' +
                ", amount = " + amount +
                ", status = '" + status + '\'' +
                '}';
    }
}
