package group9.sfursmeetingapplication.models;

import jakarta.persistence.*;

@Entity
@Table(name="mediums")
public class Medium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mid; 
    private String name; 
    private Boolean remote; 

    public Medium() {

    }

    public Medium(String name, Boolean remote) {
        this.name = name; 
        this.remote = remote; 
    }

    public Medium(int mid, String name, Boolean remote) {
        this.mid = mid; 
        this.name = name; 
        this.remote = remote; 
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getRemote() {
        return remote;
    }

    public void setRemote(Boolean remote) {
        this.remote = remote;
    }


}
