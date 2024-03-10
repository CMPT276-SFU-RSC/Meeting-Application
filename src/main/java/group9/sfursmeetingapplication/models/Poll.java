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
    private int date;
    private int startDate;
    private int endDate;
    
    public Poll() {
    }
    public Poll(String title, String description, int date, int startDate, int endDate) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public Poll(int pid, String title, String description, int date, int startDate, int endDate) {
        this.pid = pid;
        this.title = title;
        this.description = description;
        this.date = date;
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
    public int getDate() {
        return date;
    }
    public void setDate(int date) {
        this.date = date;
    }
    public int getStartDate() {
        return startDate;
    }
    public void setStartDate(int startDate) {
        this.startDate = startDate;
    }
    public int getEndDate() {
        return endDate;
    }
    public void setEndDate(int endDate) {
        this.endDate = endDate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}
