package boodschappenlijst.entity;

import javax.persistence.*;

@Entity
public class BoodschappenlijstItem {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Boolean done;

    @Column(nullable = false)
    private String item;

    @Column(nullable = false)
    private String winkel;

    protected BoodschappenlijstItem() {
        //no-args constructor required by JPA spec.
        //this one is protected since it shouldn't be used directly
    }

    public BoodschappenlijstItem(Boolean done, String item, String winkel) {
        this.done = done;
        this.item = item;
        this.winkel = winkel;
    }

    public Long getId() {
        return id;
    }

    public Boolean isDone() {
        return done;
    }

    public String getItem() {
        return item;
    }

    public String getWinkel() {
        return winkel;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setWinkel(String winkel) {
        this.winkel = winkel;
    }
}
