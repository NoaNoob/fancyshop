package de.noanoob.fancyshop;

import java.util.concurrent.ExecutionException;

import org.junit.Test;

public class FancyShopTest {

	@Test
	public void shouldStartShopping() throws InterruptedException, ExecutionException {

		new FancyShop().startShopping();
	}

}
