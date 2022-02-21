package com.qa.ims.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order_basket;
import com.qa.ims.utils.DBUtils;

public class order_basketDAO {

	public static final Logger LOGGER = LogManager.getLogger();

	public Order_basket modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long fkorderID = resultSet.getLong("fkOrderID");
		Long fkItemID = resultSet.getLong("fkItemID");
		Long quantity = resultSet.getLong("quantity");
		return new Order_basket(fkorderID, fkItemID, quantity);
	}

	/**
	 * Reads all customers from the database
	 * 
	 * @return A list of customers
	 */
	//unneeded for this class for now. can be repurposed 
/*
//	public List<Order_basket> readAll() {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				Statement statement = connection.createStatement();
//				ResultSet resultSet = statement.executeQuery("SELECT * FROM order_basket ");) {
//			List<Orders> orders= new ArrayList<>();
//			while (resultSet.next()) {
//				orders.add(modelFromResultSet(resultSet));
//			}
//			return orders;
//		} catch (SQLException e) {
//			LOGGER.debug(e);
//			LOGGER.error(e.getMessage());
//		}
//		return new ArrayList<>();
//	}
*/
	public Order_basket readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement
						.executeQuery("SELECT * FROM order_basket ORDER BY fkorderID DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Creates a customer in the database
	 * 
	 * @param order_basket - ttake
	 */
//
//	public Order_basket create(Order_basket order_basket) {
//		try (Connection connection = DBUtils.getInstance().getConnection();
//				PreparedStatement statement = connection
//						.prepareStatement("INSERT INTO order_basket(fkorderID,fkItemID,quantity) VALUES (?, ?, ?)");) {
//			statement.setLong(1, order_basket.getfkOrderID());
//			statement.setLong(2, order_basket.getfkItemID());
//			statement.setLong(3, order_basket.getQuantity());
//
//			statement.executeUpdate();
//			return readLatest();
//		} catch (Exception e) {
//			LOGGER.debug(e);
//			LOGGER.error(e.getMessage());
//		}
//		return null;
//	}

	public Order_basket read(Long id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("SELECT * FROM order_basket WHERE fkorderID = ?");) {
			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Updates a customer in the database
	 * 
	 * @param customer - takes in a customer object, the id field will be used to
	 *                 update that customer in the database
	 * @return
	 */
	public Order_basket update(Order_basket order_basket) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement(
						"UPDATE order_basket SET fkItemID = ?, quantity = ?,  WHERE fkorderID = ?");) {

			statement.setLong(1, order_basket.getfkOrderID());
			statement.setLong(2, order_basket.getfkItemID());
			statement.setLong(3, order_basket.getQuantity());

			statement.executeUpdate();
			return read(order_basket.getfkOrderID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	public Order_basket orderitem(Order_basket order_basket) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO order_basket(fkorderID,fkItemID,quantity) VALUES (?,?,?)");) {
			statement.setLong(1, order_basket.getfkOrderID());
			statement.setLong(2, order_basket.getfkItemID());
			statement.setLong(3, order_basket.getQuantity());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());

		}
		return null;
	}

	/**
	 * Deletes a customer in the database
	 * 
	 * @param id - id of the customer
	 */
	public Order_basket deleteItemfromBasket( Long fkItemID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("DELETE  FROM order_basket WHERE fkItemID = ? ");) {
			
			statement.setLong(1, fkItemID);
			statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	public String modelFromResultSetOT(ResultSet resultSetCost) throws SQLException {
		
		
		return resultSetCost.getLong("fkOrderID") + "basket total cost:" + resultSetCost.getLong("Ordertotal");
		
	}
	public String CalculateTotal(Long fkorderID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT ob.fkorderID,SUM(i.item_price*ob.quantity) AS Ordertotal FROM order_basket ob JOIN items i ON i.id=ob.fkItemID WHERE ob.fkorderID=? GROUP by ob.fkorderID;");) {
			statement.setLong(1, fkorderID);
			ResultSet resultSetCost = statement.executeQuery();
			resultSetCost.next();
			return modelFromResultSetOT(resultSetCost);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}
