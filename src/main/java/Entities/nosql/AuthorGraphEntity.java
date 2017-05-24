
package Entities.nosql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import javax.persistence.GenerationType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class AuthorGraphEntity implements Serializable {

    @Id
    private Long id;

    private String authorName;

    public AuthorGraphEntity() {
    }

    public AuthorGraphEntity(String authorName) {
        this.authorName = authorName;
    }

    public Long getId() {
        return id;
    }
     public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    
    
    
    


    
}
