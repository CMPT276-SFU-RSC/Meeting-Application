package group9.sfursmeetingapplication.models;

import jakarta.persistence.*;

@Entity
@Table(name="polls")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private String title;
    private String description;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    
    public Poll() {
    }
    public Poll(String title, String description,  java.sql.Date startDate,  java.sql.Date endDate) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Poll(int pid, String title, String description,  java.sql.Date startDate, java.sql.Date endDate) {
        this.pid = pid;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public java.sql.Date getStartDate() {
        return startDate;
    }
    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }
    public java.sql.Date getEndDate() {
        return endDate;
    }
    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
