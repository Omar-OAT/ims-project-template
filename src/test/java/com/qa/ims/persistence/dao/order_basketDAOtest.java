package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Order_basket;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

@RunWith(MockitoJUnitRunner.class)
public class order_basketDAOtest {

	private final order_basketDAO DAO = new order_basketDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/MyTestSchema.sql", "src/test/resources/MyTestData.sql");

	}

	@Test
	public void testReadAll() {
		List<Orders> expected = new ArrayList<>();
		expected.add(new Orders(1L));

		assertEquals(expected, DAO.readAll());
		
	}

	@Test
	public void testReadLatest() {
		
		assertEquals(new Orders(1L), DAO.readLatest());
	}

	@Test
	public void testCreate() {
		final Order_basket created = new Order_basket(1L,10L);
		assertEquals(created, DAO.orderitem(created));
	}

	@Test
	public void testRead() {
		final Long ID = 3L;
		assertEquals(new Orders(ID), DAO.read(ID));

	}

	@Test
	public void testUpdate() {
		final Orders updated = new Orders(1L,79L);
		assertEquals(updated, DAO.update(updated));
	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}

}
