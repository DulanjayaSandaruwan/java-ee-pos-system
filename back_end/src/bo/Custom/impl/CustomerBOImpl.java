package bo.Custom.impl;

import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 19/05/2022
 **/
public class CustomerBOImpl implements bo.Custom.CustomerBO {
    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addNewCustomer(CustomerDTO dto, DataSource d) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(dto.getCustId(), dto.getCustName(), dto.getCustAddress(), dto.getContact()),
                d);
    }

    @Override
    public JsonArrayBuilder loadAllCustomerforTable(DataSource d) throws SQLException, ClassNotFoundException {
        return customerDAO.getAll(d);
    }

    @Override
    public boolean deleteCustomer(String id, DataSource dataSource) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id, dataSource);
    }

    @Override
    public boolean updateCustomer(CustomerDTO c, DataSource dataSource) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(c.getCustId(), c.getCustName(), c.getCustAddress(),
                c.getContact()), dataSource);
    }

    @Override
    public JsonObjectBuilder getCustomer(String id, DataSource dataSource) throws SQLException, ClassNotFoundException {
        return null;
    }
}
