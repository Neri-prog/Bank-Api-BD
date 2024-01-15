package br.com.neri.persistence.dao;

import java.util.List;
import java.util.Optional;


public interface Dao<T> {

    Optional<T> get(Long id);

    List<T> getAll();

    void save(T data);

    void update(Object object);

    void delete(Long id);

}
