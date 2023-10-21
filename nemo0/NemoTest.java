package nemo0;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
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
	
	@Test public void test03NemoDirectionStartsOnRight() {
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
	
	@Test public void test09NemoRotates180DegreesToTheRight() {
        assertEquals("left", nemoDidThis("rr").getDirection());
	}
	
	@Test public void test10NemoGoesForward() {
        assertEquals(new ArrayList<>(Arrays.asList(1, 0)), nemoDidThis("f").getPosition());
	}
	
	
	@Test public void test11NemoRotatesRightTwiceWhileGoingForward() {
		assertEquals(new ArrayList<>(Arrays.asList(0, -1)), nemoDidThis("frfrf").getPosition());
	}
	
	@Test public void test12NemoRotatesLeftTwiceWhileGoingForward() {
		assertEquals(new ArrayList<>(Arrays.asList(0, 1)), nemoDidThis("flflf").getPosition());
	}
	
	@Test public void test13NemoRotates360DegreesToTheLeft() {
		assertEquals("right", nemoDidThis("llll").getDirection());	
	}
	
	@Test public void test14NemoMovesOnACircleAndGoesBackToOriginalePosition() {
		Nemo nemo = nemo();
		assertEquals(new ArrayList<>(Arrays.asList(0, 0)), nemo.getPosition());
		nemo.action("frfrfrf");
		assertEquals(new ArrayList<>(Arrays.asList(0, 0)), nemo.getPosition());
	}
	
	@Test public void test15NemoThrowsCapsule() {
		assertEquals( Nemo.successfullLaunch, nemoDidThis("m").getMessage() );
	}
	
	@Test public void test16NemoThrowsCapsuleInDepth1() {
		assertEquals( Nemo.successfullLaunch, nemoDidThis("dm").getMessage() );
	}
	
	@Test public void test17NemoCantThrowsCapsuleUnderDeapth1() {
		assertEquals( Nemo.submarineHasExploded , assertThrows(Exception.class, ()-> nemoDidThis("ddm")).getMessage() );
	}
	
	@Test public void test18NemoGoesDownThreeTimesAndUpTwoAndThrowsCapsule(){
		assertEquals( Nemo.successfullLaunch, nemoDidThis("ddduum").getMessage() );
	}
	
	@Test public void test19NemoGoesDownFourTimesAndUpOneAndCantThrowsCapsule(){
		assertEquals( Nemo.submarineHasExploded , assertThrows(Exception.class, ()-> nemoDidThis("ddddum")).getMessage() );
	}
	
	@Test public void test20NemoCanMoveUnderSurface(){
		nemosStateVerification(nemoDidThis("dddfrfrf"), 3, new ArrayList<>(Arrays.asList(0, -1)), "left", Nemo.noCapsuleThrown);	
	}
	
	@Test public void test21NemoCanMoveAndThrowCapsulesInDepth1() {
		nemosStateVerification(nemoDidThis("dmfrfrf"), 1, new ArrayList<>(Arrays.asList(0, -1)), "left", Nemo.successfullLaunch);
	}
	
	@Test public void test22NemoCanThrowCapsulesMoreThanOneTime() {
		assertEquals( 4 , nemoDidThis("mmmm").thrownCapsules );
	}
	
	@Test public void test23NemoDoesSeveralIndicationsAtTheSameTimeInSurface() {
		nemosStateVerification(nemoDidThis("flffrrfm"), 0, new ArrayList<>(Arrays.asList(1, 1)), "down", Nemo.successfullLaunch);
	}
	
	@Test public void test24NemoDoesSeveralIndicationsAtTheSameTimeOnDepth() {
		nemosStateVerification(nemoDidThis("ddduflffrrf"), 2, new ArrayList<>(Arrays.asList(1, 1)), "down", Nemo.noCapsuleThrown);
	}
	
	@Test public void test25NemoDoesSeveralIndicationsBothAtOnDepthAndSurface() {
		nemosStateVerification(nemoDidThis("flffddduurrfm"), 1, new ArrayList<>(Arrays.asList(1, 1)), "down", Nemo.successfullLaunch);
	}


	private Nemo nemo() {
		Nemo nemo = new Nemo();
		return nemo;
	}
	private Nemo nemoDidThis(String action) {
		Nemo nemo = nemo();
        nemo.action(action);
		return nemo;
	}
	private void nemosStateVerification(Nemo nemo,int expectedDepth, ArrayList<Integer> expectedPosition, String expectedDirection, String expectedMessage) {
		assertEquals(expectedDepth, nemo.getDepth());
	    assertEquals(expectedPosition, nemo.getPosition());
	    assertEquals(expectedDirection, nemo.getDirection());
	    assertEquals(expectedMessage, nemo.getMessage());
	}
}
