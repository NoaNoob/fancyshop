package de.noanoob.fancyshop.customer;

import java.util.Random;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Customer {

	static String[] firstnames = { "Anna", "Berta", "Marcus", "Peter", "Olga", "Maria", "Thomas", "Trevor" };
	static String[] lastnames = { "van Damme", "Schmidt", "Cesaré", "Mc Gillen", "Baleone", "Slammyman" };
	static Random random = new Random();

	private String firstname;
	private String lastname;
	private int age;

	public static Customer createRandomCustomer() {

		return Customer.builder().firstname(random(firstnames)).lastname(random(lastnames)).age(18 + random.nextInt(50))
				.build();
	}

	private static String random(String[] array) {

		return array[random.nextInt(array.length - 1)];

	}

}
