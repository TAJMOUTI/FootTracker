package fr.android.foottracker.database.DAO;

import java.util.List;

public interface DAO <T> {

    T get(int id);
    List<T> getAll();
    T insert(T object);
    T modify(T object);

}
