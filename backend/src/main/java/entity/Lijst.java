package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "lijst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lijst.findAll", query = "SELECT l FROM Lijst l"),
    @NamedQuery(name = "Lijst.findById", query = "SELECT l FROM Lijst l WHERE l.id = :id"),
    @NamedQuery(name = "Lijst.findByDone", query = "SELECT l FROM Lijst l WHERE l.done = :done")})
public class Lijst implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "done")
    private Boolean done;
    @Lob
    @Size(max = 65535)
    @Column(name = "item")
    private String item;
    @Lob
    @Size(max = 65535)
    @Column(name = "winkel")
    private String winkel;

    public Lijst() {
    }

    public Lijst(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getWinkel() {
        return winkel;
    }

    public void setWinkel(String winkel) {
        this.winkel = winkel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lijst)) {
            return false;
        }
        Lijst other = (Lijst) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Lijst[ id=" + id + " ]";
    }
    
}
