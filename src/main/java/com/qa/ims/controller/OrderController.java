package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrderDAO;
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

	public OrderController(OrderDAO orderDAO, Utils utils) {
		super();
		this.orderDAO = orderDAO;
		this.utils = utils;
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
		LOGGER.info("Please enter an order id");
		Long id = utils.getLong();
		LOGGER.info("Please enter a customer ID");
		Long fkCustID = utils.getLong();
		Orders order = orderDAO.create(new Orders(id, fkCustID));
		LOGGER.info("Customer created");
		return order;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Orders update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter a customer ID");
		Long fkCustID = utils.getLong();
		Orders order = orderDAO.update(new Orders(id, fkCustID));
		LOGGER.info("Order Updated");
		return order;
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
