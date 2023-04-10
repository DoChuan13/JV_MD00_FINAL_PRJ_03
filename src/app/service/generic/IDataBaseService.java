package app.service.generic;

import java.util.List;

public interface IDataBaseService<T> {
    //Update Database
    public void updateDatabase(String pathName, List<T> t);
}
