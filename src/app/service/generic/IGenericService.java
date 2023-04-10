package app.service.generic;

import java.util.List;

public interface IGenericService<T extends Object> {
    //Return All Value
    List<T> findAll();

    //Add New Object & Update Current Object
    void save(T t);

    //Find Object By Id
    T findById(int id);

    //Delete Object By Id
    T delete(int id);
}