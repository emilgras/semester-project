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
 * @author abj
 */
@Entity
@Table(name = "wrote")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Wrote.findAll", query = "SELECT w FROM Wrote w")
    , @NamedQuery(name = "Wrote.findByWroteID", query = "SELECT w FROM Wrote w WHERE w.wroteID = :wroteID")})
public class Wrote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WroteID")
    private Integer wroteID;
    @JoinColumn(name = "BookId", referencedColumnName = "BookID")
    @ManyToOne
    private Books bookId;
    @JoinColumn(name = "AuthorId", referencedColumnName = "AuthorID")
    @ManyToOne
    private Authors authorId;

    public Wrote() {
    }

    public Wrote(Integer wroteID) {
        this.wroteID = wroteID;
    }

    public Integer getWroteID() {
        return wroteID;
    }

    public void setWroteID(Integer wroteID) {
        this.wroteID = wroteID;
    }

    public Books getBookId() {
        return bookId;
    }

    public void setBookId(Books bookId) {
        this.bookId = bookId;
    }

    public Authors getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Authors authorId) {
        this.authorId = authorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (wroteID != null ? wroteID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wrote)) {
            return false;
        }
        Wrote other = (Wrote) object;
        if ((this.wroteID == null && other.wroteID != null) || (this.wroteID != null && !this.wroteID.equals(other.wroteID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.sql.Wrote[ wroteID=" + wroteID + " ]";
    }
    
}
