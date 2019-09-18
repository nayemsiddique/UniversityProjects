/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.questionthreeb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 *
 * @author nayem
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of ={"code"})
public class course {
    private String code;
    private String title;
}
