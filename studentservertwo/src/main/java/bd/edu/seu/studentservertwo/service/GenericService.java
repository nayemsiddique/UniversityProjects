package bd.edu.seu.studentservertwo.service;

import bd.edu.seu.studentservertwo.exception.ResourceAlreadyExistsException;
import bd.edu.seu.studentservertwo.exception.ResourceDoesnotExistsException;

import java.util.List;

public interface GenericService<T, I> {
    T create(T t) throws ResourceAlreadyExistsException;

    T update(I i, T t) throws ResourceDoesnotExistsException;

    List<T> findAll();

    T findById(I i);

    boolean delete(T t) throws ResourceDoesnotExistsException;
}
