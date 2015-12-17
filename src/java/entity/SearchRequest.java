
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author CosticaTeodor
 */
@Entity
public class SearchRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String noHits;
    private String to_;
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTo() {
        return to_;
    }

    public void setTo(String to_) {
        this.to_ = to_;
    }

    public String getNoHits() {
        return noHits;
    }

    public void setNoHits(String noHits) {
        this.noHits = noHits;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SearchRequest)) {
            return false;
        }
        SearchRequest other = (SearchRequest) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.SearchRequest[ id=" + id + " ]";
    }

}
