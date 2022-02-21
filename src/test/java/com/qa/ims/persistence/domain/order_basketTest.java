package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class order_basketTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Order_basket.class).verify();
	}

}
