package group9.sfursmeetingapplication.models;

import jakarta.persistence.*;

@Entity
@Table(name="polls")
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;
    private long creator_id;
    private String title;
    private String description;

    private java.time.Instant startDate;
    private java.time.Instant endDate;
    private java.time.Instant expirary;

    public Poll() {
    }


    public Poll(int pid, String title, String description,  java.time.Instant startDate, java.time.Instant endDate, java.time.Instant expirary) {
        this.pid = pid;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expirary = expirary;

    }

    public Poll(int pid, long creator_id, String title, String description, java.time.Instant startDate, java.time.Instant endDate,java.time.Instant expirary) {
        this.pid = pid;
        this.creator_id = creator_id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expirary = expirary;
    }




    public java.time.Instant getExpirary() {
        return expirary;
    }
    public void setExpirary(java.time.Instant expirary) {
        this.expirary = expirary;
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
    public java.time.Instant getStartDate() {
        return startDate;
    }
    public void setStartDate(java.time.Instant startDate) {
        this.startDate = startDate;
    }
    public java.time.Instant getEndDate() {
        return endDate;
    }
    public void setEndDate(java.time.Instant endDate) {
        this.endDate = endDate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }


    public long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(long creator_id) {
        this.creator_id = creator_id;
    }

}
