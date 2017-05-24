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
@Table(name = "books")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b")
    , @NamedQuery(name = "Books.findByBookID", query = "SELECT b FROM Books b WHERE b.bookID = :bookID")
    , @NamedQuery(name = "Books.findByTitle", query = "SELECT b FROM Books b WHERE b.title = :title")})
public class Books implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "BookID")
    private String bookID;
    @Size(max = 200)
    @Column(name = "Title")
    private String title;
    @OneToMany(mappedBy = "bookId")
    private Collection<Mentions> mentionsCollection;
    @OneToMany(mappedBy = "bookId")
    private Collection<Wrote> wroteCollection;

    public Books() {
    }

    public Books(String bookID) {
        this.bookID = bookID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlTransient
    public Collection<Mentions> getMentionsCollection() {
        return mentionsCollection;
    }

    public void setMentionsCollection(Collection<Mentions> mentionsCollection) {
        this.mentionsCollection = mentionsCollection;
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
        hash += (bookID != null ? bookID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Books)) {
            return false;
        }
        Books other = (Books) object;
        if ((this.bookID == null && other.bookID != null) || (this.bookID != null && !this.bookID.equals(other.bookID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.sql.Books[ bookID=" + bookID + " ]";
    }
    
}
