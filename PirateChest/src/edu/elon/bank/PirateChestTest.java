package edu.elon.bank;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PirateChestTest {
	private PirateChest chest;
	
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Seeting up chest");
		chest = new PirateChest(100);
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("Cleaning up chest");
		chest = null;
	}

	@Test
	void testAddGold() {
		//PirateChest chest = new PirateChest(100);
		chest.addGold(30);
		int actual = chest.checkGold();
		int expected = 130;
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void testCheckGold() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsObject() {
		//PirateChest chest = new PirateChest(100);
		PirateChest chest2 = new PirateChest(100);
		Boolean actual = chest.equals(chest2);
		Boolean expected = true;
		Assertions.assertEquals(expected, actual);
		
		chest2.addGold(50);
		actual = chest.equals(chest2);
		expected = false;
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void testRemoveGold() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		//PirateChest chest = new PirateChest(100);
		String actual = chest.toString();
		String expected = "Chest has 100 pieces of gold";
		Assertions.assertEquals(expected, actual);
	}

}
