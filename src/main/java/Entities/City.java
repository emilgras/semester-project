/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author abj
 */

/**
 * 
 * TODO: Insert some magic in this class
 */
@Entity
public class City implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;
    
    
}
