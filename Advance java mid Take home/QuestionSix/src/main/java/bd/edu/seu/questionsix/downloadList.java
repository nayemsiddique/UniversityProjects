/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.questionsix;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
public class downloadList {
    @Id
    private String downloadId;
    @ManyToOne
    private project projectId;
    @ManyToOne
    private user userId;
    private LocalDate downloadDate;
}
