package nemo0;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class NemoTest<E> {
	@Test public void test01NemoShouldBeOnSurface() {
	    assertTrue(nemo().onSurface());
	  }
	
	@Test public void test02GetNemoPosition() {
		assertEquals( new ArrayList<>(Arrays.asList(0, 0)) , nemo().getPosition());
	}
	
	@Test public void test03NemoDirection() {
		assertEquals("right", nemo().getDirection());
	}
	
	@Test public void test04NemoDoesNotMove() {
		assertEquals( new ArrayList<>(Arrays.asList(0, 0)), nemoDidThis(" ").getPosition());
	}
	
	@Test public void test05NemoDescends() {
        assertEquals(1, nemoDidThis("d").getDepth());
	}
	
	@Test public void test06NemoDoesntFly() {
        assertEquals(0, nemoDidThis("u").getDepth());
	}
	
	@Test public void test07NemoRotates90DegreesToTheLeft() {
        assertEquals("up", nemoDidThis("l").getDirection());
	}
	
	@Test public void test08NemoRotates90DegreesToTheRight() {
        assertEquals("down", nemoDidThis("r").getDirection());
	}
	
	@Test public void test09NemoGoesForward() {
        assertEquals(new ArrayList<>(Arrays.asList(1, 0)), nemoDidThis("f").getPosition());
	}
	
	@Test public void test10NemoDoesSeveralIndicationsAtTheSameTimeInSurface() {
		nemosStateVerification(nemoDidThis("flffrrf"), 0, new ArrayList<>(Arrays.asList(1, 1)), "down", Nemo.noCapsuleThrown );
	}
	
	@Test public void test11NemoDoesSeveralIndicationsAtTheSameTime() {
		nemosStateVerification(nemoDidThis("flfdddufrrf"), 2, new ArrayList<>(Arrays.asList(1, 1)), "down", Nemo.noCapsuleThrown);
	}
	
	@Test public void test12NemoThrowsCapsule() {
		assertEquals( Nemo.successfullLaunch, nemoDidThis("dm").getMessage() );
	}
	
	@Test public void test13NemoCantThrowsCapsuleUnderDeapth1() {
		assertEquals( Nemo.submarineHasExploded , nemoDidThis("ddm").getMessage() );
	}
	
	@Test public void test15NemoCanKeepGoingAfterThrowingCapsuleRight() {
		assertEquals( Nemo.successfullLaunch, nemoDidThis("dmfrfudum").getMessage());
	}

	@Test
	public void test17NemoIgnoresUnknownCommands() {
	    nemosStateVerification(nemoDidThis("XhpO"), 0, new ArrayList<>(Arrays.asList(0, 0)), "right", Nemo.noCapsuleThrown );
	
	}
	@Test
	public void test18NemoIgnoresUnknownCommandsButDoesFollowsKnownOnes() {
	    nemosStateVerification(nemoDidThis("XdXdXXffXXlfXXumX"), 1, new ArrayList<>(Arrays.asList(2, 1)), "up", Nemo.successfullLaunch);
	}
	
	@Test
	public void test19NemoIgnoresEmptySpaces() {
	    nemosStateVerification(nemoDidThis(" d d  ff  lf  um"), 1, new ArrayList<>(Arrays.asList(2, 1)), "up", Nemo.successfullLaunch);
	}
	
	
	private Nemo<E> nemo() {
		Nemo<E> nemo = new Nemo<>();
		return nemo;
	}
	private Nemo<E> nemoDidThis(String action) {
		Nemo<E> nemo = nemo();
        nemo.action(action);
		return nemo;
	}
	private void nemosStateVerification(Nemo<E> nemo,int expectedDepth, ArrayList<Integer> expectedPosition, String expectedDirection, String expectedMessage) {
		assertEquals(expectedDepth, nemo.getDepth());
	    assertEquals(expectedPosition, nemo.getPosition());
	    assertEquals(expectedDirection, nemo.getDirection());
	    assertEquals(expectedMessage, nemo.getMessage());
	}
}
