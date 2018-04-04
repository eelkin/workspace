package ch10state2;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		State expected = new UnlockedState(ts);
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
		String expectedS = "sounding the alarm";
		Assertions.assertEquals(expectedS, actualS);
		
		ts.insertCoin();
		ts.pass();
		State actual = ts.getState();
		State expected = new LockedState(ts);
		Assertions.assertEquals(expected, actual);
	}

}
