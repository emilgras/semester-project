/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.sql;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Frederik
 */
@Entity
@Table(name = "mentions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mentions.findAll", query = "SELECT m FROM Mentions m")
    , @NamedQuery(name = "Mentions.findByMentionID", query = "SELECT m FROM Mentions m WHERE m.mentionID = :mentionID")})
public class Mentions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MentionID")
    private Integer mentionID;
    @JoinColumn(name = "BookId", referencedColumnName = "BookID")
    @ManyToOne
    private Books bookId;
    @JoinColumn(name = "CityId", referencedColumnName = "CityID")
    @ManyToOne
    private Cities cityId;

    public Mentions() {
    }

    public Mentions(Integer mentionID) {
        this.mentionID = mentionID;
    }

    public Integer getMentionID() {
        return mentionID;
    }

    public void setMentionID(Integer mentionID) {
        this.mentionID = mentionID;
    }

    public Books getBookId() {
        return bookId;
    }

    public void setBookId(Books bookId) {
        this.bookId = bookId;
    }

    public Cities getCityId() {
        return cityId;
    }

    public void setCityId(Cities cityId) {
        this.cityId = cityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mentionID != null ? mentionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mentions)) {
            return false;
        }
        Mentions other = (Mentions) object;
        if ((this.mentionID == null && other.mentionID != null) || (this.mentionID != null && !this.mentionID.equals(other.mentionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.sql.Mentions[ mentionID=" + mentionID + " ]";
    }
    
}
