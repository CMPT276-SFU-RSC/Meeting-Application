package group9.sfursmeetingapplication.models;

import jakarta.persistence.*;

@Entity
@Table(name="invited")
public class Invited {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iid;//not used other than indexing
    private int pid;
    private int uid;


    public Invited() {
    }
    public Invited(int pid, int uid, int iid) {
        this.pid = pid;
        this.uid = uid;
        this.iid = iid;
    }

    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
    public int getIid() {
        return iid;
    }
    public void setIid(int iid) {
        this.iid = iid;
    }

}
