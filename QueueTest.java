package queue;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class QueueTest {
	private String something = "Something";
	private String firstElement = "First";
	private String secondElement = "Second";
	
  @Test public void test01QueueShouldBeEmptyWhenCreated() {
    assertTrue( emptyQueue().isEmpty() );
  }

  @Test public void test02AddElementsToTheQueue() {
    assertFalse( queueWithSomething().isEmpty() );
  }

  @Test public void test03AddedElementsIsAtHead() {
	assertEquals( something, queueWithSomething().head() );
  }
  
  @Test public void test04TakeRemovesElementsFromTheQueue() {
    Queue queue = queueWithSomething();
    queue.take();
    assertTrue( queue.isEmpty() );
  }

  @Test public void test05TakeReturnsLastAddedObject() {
    assertEquals( something , queueWithSomething().take() );
  }

  @Test public void test06QueueBehavesFIFO() {
    Queue queue = queueWithTwoElements();
    assertEquals(queue.take(), firstElement);
    assertEquals(queue.take(), secondElement);
    assertTrue(queue.isEmpty() );
  }
  
  @Test public void test07HeadReturnsFirstAddedObject() {
    Queue queue = queueWithTwoElements();

    assertEquals(queue.head(), firstElement);
  }
  
  @Test public void test08HeadDoesNotRemoveObjectFromQueue() {
    Queue queue = queueWithSomething();
    assertEquals( 1, queue.size() );
    queue.head();
    assertEquals( 1, queue.size() );
  }
  @Test public void test09SizeRepresentsObjectInTheQueue() {
    assertEquals( 2, queueWithTwoElements().size() );
  }
  
  @Test public void test10CanNotTakeWhenThereAreNoObjectsInTheQueue() {
	  assertTrue( assertThrows( Error.class,() -> emptyQueue().take() ).getMessage().equals( Queue.QueueEmpty ));
  }
  
@Test public void test09CanNotTakeWhenThereAreNoObjectsInTheQueueAndTheQueueHadObjects() {
    Queue queue = queueWithSomething();
    queue.take();
    assertTrue( assertThrows( Error.class, ()-> queue.take() ).getMessage().equals( Queue.QueueEmpty  ) );
  }

  @Test public void test10CanNotHeadWhenThereAreNoObjectsInTheQueue() {
	  assertTrue( assertThrows( Error.class, ()-> emptyQueue().head() ).getMessage().equals( Queue.QueueEmpty ) );
  }
  
  private Queue queueWithSomething() {
		return emptyQueue().add( something );
	}
  private Queue emptyQueue() {
		return new Queue();
	}
  private Queue queueWithTwoElements() {
		return emptyQueue().add( firstElement ).add( secondElement );
	}
}
