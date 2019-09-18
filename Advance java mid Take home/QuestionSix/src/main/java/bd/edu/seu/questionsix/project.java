/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.questionsix;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author nayem
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class project {
    @Id
    //@Size(min=0,max=8,message = "m")
    private String projectId;
    
    @Enumerated(EnumType.STRING)
    private categories categorie;
    
    @Enumerated(EnumType.STRING)
    private status status;
    
    private String description;

    @Override
    public String toString() {
        return projectId;
    }
    
    
}
