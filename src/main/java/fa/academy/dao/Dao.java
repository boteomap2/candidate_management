package fa.academy.dao;

import java.util.ArrayList;

public interface Dao<T> {
    T find(String id);
    ArrayList<T> findAll();
    void save(T t);
    void update(T t);
    void delete(String t);
}
