/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.questionsix;

import javax.persistence.Embeddable;
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
@Embeddable
public class name {
    private String firstName;
    private String lastName;
    
}
