/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.edu.seu.questionsix;

import java.util.List;

/**
 *
 * @author nayem
 */
public interface CrudDAO<T,I> {
   T create(T t);
    List<T>retrive();
    T retrive(I id);
    boolean delete(T t);
}
