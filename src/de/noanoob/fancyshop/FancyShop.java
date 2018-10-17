package de.noanoob.fancyshop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import de.noanoob.fancyshop.customer.Customer;
import de.noanoob.fancyshop.item.Item;
import de.noanoob.fancyshop.order.Order;

public class FancyShop {

	public static final int SPEED = 1;

	private List<Order> orders = new ArrayList<>();

	public void startShopping() throws InterruptedException, ExecutionException {

		long start = System.currentTimeMillis();

		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(2);
		ThreadPoolExecutor tp = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, workQueue,
				new ThreadPoolExecutor.CallerRunsPolicy());

		for (int count = 0; count < 100; count++) {

			Runnable run = () -> {
				try {
					shop();
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			tp.submit(run);
		}

		Thread.sleep(10);
		System.out.println("Shopping lastet " + (System.currentTimeMillis() - start));
	}

	public void shop() throws InterruptedException, ExecutionException {

		CompletableFuture<Customer> customer = CompletableFuture.supplyAsync(this::randomCustomer);
		CompletableFuture<List<Item>> items = CompletableFuture.supplyAsync(this::randomItems);

		customer.thenCombine(items, this::createOrder).thenApply(this::billing).thenAccept(this::addOrder);
	}

	void addOrder(Order order) {

		this.orders.add(order);
	}

	Order createOrder(Customer customer, List<Item> items) {

		Order order = Order.builder().customer(customer).build();
		order.getItems().addAll(items);

		return order;
	}

	public Customer randomCustomer() {

		return Customer.createRandomCustomer();
	}

	public List<Item> randomItems() {
		List<Item> list = new ArrayList<>();
		for (int i = 0; i < randomInt(); i++) {
			list.add(Item.createRandomItem());
		}
		return list;
	}

	Order billing(Order order) {

		order.bill();
		return order;
	}

	public List<Order> getOrders() {

		return orders;
	}

	private int randomInt() {
		return new Random().nextInt(20) + 1;
	}
}
