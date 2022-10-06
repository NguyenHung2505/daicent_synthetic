package managedao;

import java.sql.SQLException;
import java.util.List;

public interface GenneralDAO<T>{
    public List<T> findAll();

    public void  insert (T t);

    public T findById(int id);

    public boolean update (T t) throws SQLException;

    public boolean delete (int id) throws SQLException;
}
