package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.order_basketDAO;
import com.qa.ims.persistence.domain.Order_basket;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

/**
 * Takes in customer details for CRUD functionality
 *
 */
public class OrderController implements CrudController<Orders> {

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderDAO orderDAO;
	private Utils utils;
	private order_basketDAO order_basketDAO;

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
		order_basketDAO = new order_basketDAO();
	}

	/**
	 * Reads all customers to the logger
	 */
	@Override
	public List<Orders> readAll() {
		List<Orders> orders = orderDAO.readAll();
		for (Orders order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Orders create() {
//		LOGGER.info("Please enter an order id");
//		Long id = utils.getLong();
		LOGGER.info("Please enter a customer ID");

		Long fkCustID = utils.getLong();

		Orders order = orderDAO.create(new Orders(fkCustID));

		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing order by taking in user input and also contains the switch
	 * case for 
	 */
	@Override
	public Orders update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		long id = utils.getLong();
		LOGGER.info("What would you like to do" + "\n 1.Change customer id" + "\n 2.add an item to an order"
				+ "\n 3.delete an item form an order " + "\n 4.Basket total");
		String option = utils.getString();

		switch (option) {

		case ("1"):
			LOGGER.info("Please enter a customer ID");
			Long fkCustID = utils.getLong();
			orderDAO.update(new Orders(id, fkCustID));
			LOGGER.info("Order Updated");

			break;

		case ("2"):
			LOGGER.info("enter the item you want to add");
			Long fkItemID = utils.getLong();
			LOGGER.info("How many would you like");
			Long quantity = utils.getLong();
			order_basketDAO.orderitem(new Order_basket(id, fkItemID, quantity));
			LOGGER.info("Item added to order");
			order_basketDAO.read(id);

			break;
		case ("3"):

			LOGGER.info("Enter the item id you would like to remove");
			Long fkItemID1 = utils.getLong();

			order_basketDAO.deleteItemfromBasket(fkItemID1);
			LOGGER.info("Item removed from order");
			break;

		case ("4"):
			
			LOGGER.info("Order basket: " + id);
			LOGGER.info(order_basketDAO.CalculateTotal(id));

		}
		return null;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the customer you would like to delete");
		Long id = utils.getLong();
		return orderDAO.delete(id);
	}

}
