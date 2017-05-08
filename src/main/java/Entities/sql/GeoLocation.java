
package Entities.sql;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

public class GeoLocation implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    
    
}
