package be.bstorm.repositories;

import org.hibernate.sql.Update;

import java.util.List;

public interface CRUDRepository <T>{

    List<T> getALL();
    T getById(long id);
    void create(T element);
    T update(long id, T element);
    void Delete(long id);
}
