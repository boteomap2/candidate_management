package fa.academy.dao;

import java.util.ArrayList;

public interface Dao<T> {
    T find(String id);
    ArrayList<T> findAll();
    boolean save(T t);
    boolean update(T t);
    void delete(String t);
}
