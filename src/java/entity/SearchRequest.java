
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
    
    private String from_;
    private String to_;
    private String date_;
    private String noPassengers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from_;
    }

    public void setFrom(String from) {
        this.from_ = from;
    }

    public String getTo() {
        return to_;
    }

    public void setTo(String to) {
        this.to_ = to;
    }

    public String getDate() {
        return date_;
    }

    public void setDate(String date) {
        this.date_ = date;
    }

    public String getNoPassengers() {
        return noPassengers;
    }

    public void setNoPassengers(String noPassengers) {
        this.noPassengers = noPassengers;
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
