package dao.custom.impl;

import dao.custom.ItemDAO;
import entity.Item;

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
 * @Since : 21/05/2022
 **/
public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean add(Item item, DataSource dataSource) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Item VALUES (?,?,?,?)");
        preparedStatement.setString(1, item.getItemCode());
        preparedStatement.setString(2, item.getDescription());
        preparedStatement.setInt(3, item.getQtyOnHand());
        preparedStatement.setDouble(4, item.getUnitPrice());

        if (preparedStatement.executeUpdate() > 0) {
            connection.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String s, DataSource dataSource) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Item WHERE ItemCode=?");
        preparedStatement.setString(1,s);

        if (preparedStatement.executeUpdate() > 0) {
            connection.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Item item, DataSource dataSource) throws SQLException, ClassNotFoundException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Item SET Description=?, QtyOnHand=?, UnitPrice=? WHERE ItemCode=?");
        preparedStatement.setString(1, item.getDescription());
        preparedStatement.setInt(2, item.getQtyOnHand());
        preparedStatement.setDouble(3, item.getUnitPrice());
        preparedStatement.setString(4, item.getItemCode());

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
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Item");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            String ItemCode = resultSet.getString(1);
            String Description = resultSet.getString(2);
            String QtyOnHand = String.valueOf(resultSet.getInt(3));
            String UnitPrice = String.valueOf(resultSet.getDouble(4));

            objectBuilder.add("ItemCode", ItemCode);
            objectBuilder.add("Description", Description);
            objectBuilder.add("QtyOnHand", QtyOnHand);
            objectBuilder.add("UnitPrice", UnitPrice);

            JsonObject build = objectBuilder.build();

            arrayBuilder.add(build);
        }

        connection.close();

        return arrayBuilder;

    }
}
