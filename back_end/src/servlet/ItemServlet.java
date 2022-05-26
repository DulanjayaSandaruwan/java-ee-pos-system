package servlet;

import bo.BOFactory;
import bo.Custom.ItemBO;
import dto.ItemDTO;

import javax.annotation.Resource;
import javax.json.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * @author : D.D.Sandaruwan <dulanjayasandaruwan1998@gmail.com>
 * @Since : 20/05/2022
 **/

@WebServlet (urlPatterns = "/item")
public class ItemServlet extends HttpServlet {
    private final ItemBO itemBO = (ItemBO) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ITEM);

    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        try {
            JsonArrayBuilder jsonArrayBuilder = itemBO.loadAllItem(dataSource);
            PrintWriter writer = resp.getWriter();
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 200);
            objectBuilder.add("message", "done");
            objectBuilder.add("data", jsonArrayBuilder.build());
            writer.print(objectBuilder.build());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemCode = req.getParameter("ItemCode");
        String description = req.getParameter("Description");
        int qtyOnHand = Integer.parseInt(req.getParameter("QtyOnHand"));
        double unitPrice = Double.parseDouble(req.getParameter("UnitPrice"));

        resp.setContentType("application/json");

        ItemDTO itemDTO = new ItemDTO(itemCode, description, qtyOnHand, unitPrice);

        PrintWriter writer = resp.getWriter();

        try {
            if (itemBO.addNewItem(itemDTO, dataSource)){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_CREATED);
                objectBuilder.add("status", 200);
                objectBuilder.add("message", "done");
                objectBuilder.add("data", "Sucessfully Added !");
                writer.print(objectBuilder.build());
            }
        } catch (SQLException e) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            resp.setStatus(HttpServletResponse.SC_CREATED);
            objectBuilder.add("status", 400);
            objectBuilder.add("message", "done");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());
        } catch (ClassNotFoundException e) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            resp.setStatus(HttpServletResponse.SC_CREATED);
            objectBuilder.add("status", 400);
            objectBuilder.add("message", "done");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();

        resp.setContentType("application/json");

        PrintWriter writer = resp.getWriter();

        String itemCode = jsonObject.getString("ItemCode");
        String description = jsonObject.getString("Description");
        int qtyOnHand = Integer.parseInt(jsonObject.getString("QtyOnHand"));
        double unitPrice = Double.parseDouble(jsonObject.getString("UnitPrice"));

        ItemDTO itemDTO = new ItemDTO(itemCode, description, qtyOnHand, unitPrice);

        try {
            if (itemBO.updateItem(itemDTO, dataSource)){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_CREATED);
                objectBuilder.add("status", 200);
                objectBuilder.add("message", "done");
                objectBuilder.add("data", "Sucessfully Updated !");
                writer.print(objectBuilder.build());
            }
        } catch (SQLException e) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            resp.setStatus(HttpServletResponse.SC_CREATED);
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "error");
            objectBuilder.add("data", e.getLocalizedMessage() );
            writer.print(objectBuilder.build());
        } catch (ClassNotFoundException e) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            resp.setStatus(HttpServletResponse.SC_CREATED);
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "error");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());
        }


    }
}
