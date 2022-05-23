package bo.Custom;

import bo.SuperBO;
import dto.CustomerDTO;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 19/05/2022
 **/
public interface CustomerBO extends SuperBO {
    boolean addNewCustomer(CustomerDTO dto, DataSource d) throws SQLException, ClassNotFoundException;

    JsonArrayBuilder loadAllCustomerforTable(DataSource d) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(String id, DataSource dataSource) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(CustomerDTO c, DataSource dataSource) throws SQLException, ClassNotFoundException;

    JsonObjectBuilder getCustomer(String id, DataSource dataSource) throws SQLException, ClassNotFoundException;
}
