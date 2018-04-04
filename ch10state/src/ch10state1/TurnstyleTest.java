package ch10state1;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch10state1.Turnstyle.State;


class TurnstyleTest {
	Turnstyle ts;
	ByteArrayOutputStream buffer;

	@BeforeEach
	void setUp() throws Exception {
		ts = new Turnstyle();
		buffer = new ByteArrayOutputStream();
		System.setOut(new PrintStream(buffer));
	}

	@AfterEach
	void tearDown() throws Exception {
		ts = null;
	}

	@Test
	void testInsertCoin() {
		ts.insertCoin();
		State actual = ts.getState();
		State expected = State.UNLOCKED;
		Assertions.assertEquals(expected, actual);
		
		ts.insertCoin();
		String actualS = buffer.toString().trim();
		String expectedS = "thank you";
		Assertions.assertEquals(expectedS, actualS);
	}

	@Test
	void testPass() {
		ts.pass();
		String actualS = buffer.toString().trim();
		String expectedS = "*alarm blaring*";
		Assertions.assertEquals(expectedS, actualS);
		
		ts.setState(State.UNLOCKED);
		ts.pass();
		State actual = ts.getState();
		State expected = State.LOCKED;
		Assertions.assertEquals(expected, actual);
	}

}
