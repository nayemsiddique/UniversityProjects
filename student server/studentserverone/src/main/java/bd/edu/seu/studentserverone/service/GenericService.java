package bd.edu.seu.studentserverone.service;

import java.util.List;

public interface GenericService<T, I> {
    List<T> findAll();

    T findById(I i);

    boolean delete(I i);

    boolean add(T t);

}
