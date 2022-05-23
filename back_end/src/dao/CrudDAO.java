package dao;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 19/05/2022
 **/
public interface CrudDAO<T, ID> extends SuperDAO {
    boolean add(T t, DataSource dataSource) throws SQLException, ClassNotFoundException;

    boolean delete(ID id, DataSource dataSource) throws SQLException, ClassNotFoundException;

    boolean update(T t, DataSource dataSource) throws SQLException, ClassNotFoundException;

    JsonObjectBuilder search(ID id, DataSource dataSource) throws SQLException, ClassNotFoundException;

    JsonArrayBuilder getAll(DataSource dataSource) throws SQLException, ClassNotFoundException;
}
