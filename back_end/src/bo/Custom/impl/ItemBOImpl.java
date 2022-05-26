package bo.Custom.impl;

import bo.Custom.CustomerBO;
import bo.Custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.CustomerDTO;
import dto.ItemDTO;
import entity.Customer;
import entity.Item;

import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 21/05/2022
 **/
public class ItemBOImpl implements ItemBO {
    private final ItemDAO itemDAO= (ItemDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public boolean addNewItem(ItemDTO dto, DataSource d) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(dto.getItemCode(), dto.getDescription(), dto.getQtyOnHand(), dto.getUnitPrice()),
                d);
    }

    @Override
    public JsonArrayBuilder loadAllItem(DataSource d) throws SQLException, ClassNotFoundException {
        return itemDAO.getAll(d);
    }

    @Override
    public boolean deleteItem(String id, DataSource dataSource) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateItem(ItemDTO i, DataSource dataSource) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(i.getItemCode(), i.getDescription(), i.getQtyOnHand(), i.getUnitPrice()), dataSource);
    }

    @Override
    public JsonObjectBuilder getItem(String id, DataSource dataSource) throws SQLException, ClassNotFoundException {
        return null;
    }
}
