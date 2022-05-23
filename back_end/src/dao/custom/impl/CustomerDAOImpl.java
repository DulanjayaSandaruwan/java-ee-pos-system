package dao.custom.impl;

import dao.custom.CustomerDAO;
import entity.Customer;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 19/05/2022
 **/
public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer, DataSource dataSource) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?)");
        preparedStatement.setString(1, customer.getCustId());
        preparedStatement.setString(2, customer.getCustName());
        preparedStatement.setString(3, customer.getCustAddress());
        preparedStatement.setString(4, customer.getContact());

        if (preparedStatement.executeUpdate() > 0) {
            connection.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String s, DataSource dataSource) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Customer WHERE CustId=?");
        preparedStatement.setString(1,s);

        if (preparedStatement.executeUpdate() > 0) {
            connection.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Customer customer, DataSource dataSource) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Customer set CustName=?, CustAddress=?,  CustContact=? WHERE CustId=? ");
        preparedStatement.setString(4, customer.getCustId());
        preparedStatement.setString(1, customer.getCustName());
        preparedStatement.setString(2, customer.getCustAddress());
        preparedStatement.setString(3, customer.getContact());

        if (preparedStatement.executeUpdate() > 0) {
            connection.close();
            return true;
        }
        return false;
    }

    @Override
    public JsonObjectBuilder search(String s, DataSource dataSource) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public JsonArrayBuilder getAll(DataSource dataSource) throws SQLException, ClassNotFoundException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String CustId = resultSet.getString(1);
            String CustName = resultSet.getString(2);
            String CustAddress = resultSet.getString(3);
            String Contact = resultSet.getString(4);

            objectBuilder.add("Id", CustId);
            objectBuilder.add("Name", CustName);
            objectBuilder.add("Address", CustAddress);
            objectBuilder.add("Contact", Contact);

            JsonObject build = objectBuilder.build();

            arrayBuilder.add(build);
        }

        connection.close();

        return arrayBuilder;
    }
}
