package com.qa.ims.persistence.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.DBUtils;

@RunWith(MockitoJUnitRunner.class)
public class ItemDAOTest {

	private final ItemDAO DAO = new ItemDAO();

	@Before
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/MyTestSchema.sql", "src/test/resources/MyTestData.sql");

	}

	@Test
	public void testReadAll() {
		List<Item> expected = new ArrayList<>();
		expected.add(new Item(2L, "Patrick collectible", 550L));

		assertEquals(expected, DAO.readAll());
		
	}

	@Test
	public void testReadLatest() {
		
		assertEquals(new Item(2L, "Patrick collectible", 550L), DAO.readLatest());
	}

	@Test
	public void testCreate() {
		final Item created = new Item(3L,"Knew stuff", 700L);
		assertEquals(created, DAO.create(created));
	}

	@Test
	public void testRead() {
		final long ID = 3L;
		assertEquals(new Item(ID, "Knew stuff", 700L), DAO.read(ID));

	}

	@Test
	public void testUpdate() {
		final Item updated = new Item(1L, "Shirt-Rex tooth", 799L);
		assertEquals(updated, DAO.update(updated));
	}

	@Test
	public void testDelete() {
		assertEquals(1, DAO.delete(1));
	}

}
