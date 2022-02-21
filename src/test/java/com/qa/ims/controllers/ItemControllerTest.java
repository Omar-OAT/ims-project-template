package com.qa.ims.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.controller.ItemController;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;
import com.qa.ims.utils.Utils;

@RunWith(MockitoJUnitRunner.class)
public class ItemControllerTest {
	
	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Mock
	private Utils utils;

	@Mock
	private ItemDAO DAO;

	@InjectMocks
	private ItemController controller;
	private Long ID;
	private String item_name;
	
	

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(2L, "Patrick collectible", 550L));
		Mockito.when(DAO.readAll()).thenReturn(expected);

		assertEquals(expected, controller.readAll());

		Mockito.verify(DAO, Mockito.times(1)).readAll();
	}

	@Test
	public void testCreate() {
		final String item_name = "Knew stuff";
		final Long price = 700L;
		final Item created = new Item(item_name, price);

		Mockito.when(utils.getString()).thenReturn(item_name);
		Mockito.when(utils.getLong()).thenReturn(price);
		Mockito.when(DAO.create(created)).thenReturn(created);

		assertEquals(created, controller.create());
		Mockito.verify(utils,Mockito.times(1)).getString();
		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(DAO, Mockito.times(1)).create(created);
	
	}

	@Test
	public void testUpdate() {
		Item updated = new Item(1L, "Shirt-Rex tooth", 799L);

		Mockito.when(this.utils.getLong()).thenReturn(1L);
		Mockito.when(this.utils.getString()).thenReturn(updated.getItemName());
		Mockito.when(this.utils.getLong()).thenReturn(updated.getPrice());
		Mockito.when(this.DAO.update(updated)).thenReturn(updated);

		assertEquals(updated, this.controller.update());
		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.utils, Mockito.times(1)).getString();
		Mockito.verify(this.utils, Mockito.times(1)).getLong();
		Mockito.verify(this.DAO, Mockito.times(1)).update(updated);
	}

	@Test
	public void testDelete() {
		final long ID = 1L;

		Mockito.when(utils.getLong()).thenReturn(ID);
		Mockito.when(DAO.delete(ID)).thenReturn(1);

		assertEquals(1L, this.controller.delete());

		Mockito.verify(utils, Mockito.times(1)).getLong();
		Mockito.verify(DAO, Mockito.times(1)).delete(ID);
	}

}
