package de.noanoob.fancyshop.order;

import java.util.ArrayList;
import java.util.List;

import de.noanoob.fancyshop.customer.Customer;
import de.noanoob.fancyshop.item.Item;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;

@Data
@Builder
public class Order {

	Customer customer;
	final List<Item> items = new ArrayList<Item>();
	@Default
	int currentBill = 0;

	public void addItem(Item item) {

		items.add(item);
	}

	public void bill() {

		currentBill = 0;
		for (Item i : items) {

			currentBill = currentBill + i.getPrice();
		}
	}
}
