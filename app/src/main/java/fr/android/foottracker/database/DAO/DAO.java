package fr.android.foottracker.database.DAO;

import java.util.List;

public interface DAO <T> {

    T get(int id);
    T get(String name, int id);
    List<T> getAll();
    int create(T object);
    T modify(T object);

}
