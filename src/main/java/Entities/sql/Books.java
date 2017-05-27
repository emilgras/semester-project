/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities.sql;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
 * @author abj
 */
@Entity
@Table(name = "books")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Books.findAll", query = "SELECT b FROM Books b")
    , @NamedQuery(name = "Books.findByBookID", query = "SELECT b FROM Books b WHERE b.bookID = :bookID")
    , @NamedQuery(name = "Books.findByTitle", query = "SELECT b FROM Books b WHERE b.title = :title")

})
@NamedNativeQueries({
    @NamedNativeQuery(name ="Books.findBookFFS", query="select books.Title from books B JOIN authors a on where authors.AuthorName = :authorName")
})
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
    private List<Mentions> mentionsList;
    @OneToMany(mappedBy = "bookId")
    private List<Wrote> wroteList;

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
    public List<Mentions> getMentionsList() {
        return mentionsList;
    }

    public void setMentionsList(List<Mentions> mentionsList) {
        this.mentionsList = mentionsList;
    }

    @XmlTransient
    public List<Wrote> getWroteList() {
        return wroteList;
    }

    public void setWroteList(List<Wrote> wroteList) {
        this.wroteList = wroteList;
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
