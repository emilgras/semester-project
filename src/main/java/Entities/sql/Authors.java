/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.sql;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Frederik
 */
@Entity
@Table(name = "authors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Authors.findAll", query = "SELECT a FROM Authors a")
    , @NamedQuery(name = "Authors.findByAuthorID", query = "SELECT a FROM Authors a WHERE a.authorID = :authorID")
    , @NamedQuery(name = "Authors.findByAuthorName", query = "SELECT a FROM Authors a WHERE a.authorName = :authorName")})
public class Authors implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AuthorID")
    private Integer authorID;
    @Size(max = 200)
    @Column(name = "AuthorName")
    private String authorName;
    @OneToMany(mappedBy = "authorId")
    private Collection<Wrote> wroteCollection;

    public Authors() {
    }

    public Authors(Integer authorID) {
        this.authorID = authorID;
    }

    public Integer getAuthorID() {
        return authorID;
    }

    public void setAuthorID(Integer authorID) {
        this.authorID = authorID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @XmlTransient
    public Collection<Wrote> getWroteCollection() {
        return wroteCollection;
    }

    public void setWroteCollection(Collection<Wrote> wroteCollection) {
        this.wroteCollection = wroteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authorID != null ? authorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authors)) {
            return false;
        }
        Authors other = (Authors) object;
        if ((this.authorID == null && other.authorID != null) || (this.authorID != null && !this.authorID.equals(other.authorID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.sql.Authors[ authorID=" + authorID + " ]";
    }
    
}
