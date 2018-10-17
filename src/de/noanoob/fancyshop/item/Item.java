package de.noanoob.fancyshop.item;

import java.util.Random;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Item {

	static String[] colours = { "rot", "gelb", "blau", "grün", "orange", "schwarz" };
	static String[] names = { "Pullover", "Hose", "Schlüppi", "Mütze", "Schuhe" };
	static Random random = new Random();

	String colour;
	String name;
	int price;

	public static Item createRandomItem() {

		return Item.builder().colour(random(colours)).name(random(names)).price(random.nextInt(150)).build();
	}

	private static String random(String[] array) {

		return array[random.nextInt(array.length - 1)];

	}
}
