package project.bfour.debtormaintenance.model;

import java.io.Serializable;

public class Transaction implements Serializable {
    private String debtorId;
    private String id;
    private String dateTime;
    private String status;
    private String information;

    public Transaction() {
    }

    public Transaction(String debtorId, String id, String dateTime, String status, String information) {
        this.debtorId = debtorId;
        this.id = id;
        this.dateTime = dateTime;
        this.status = status;
        this.information = information;
    }

    public String getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(String debtorId) {
        this.debtorId = debtorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

}
