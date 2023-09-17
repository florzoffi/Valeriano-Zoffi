package queue;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;


public class Queue {
    public List<Object> elements;
    public static String QueueEmpty = "Queue is empty";
    private QueueIntervention intervention;
    public Deque<QueueIntervention> stateHistory;

    public Queue() {
        elements = new ArrayList<>();
        intervention = new TEmpty(this);
        stateHistory = new ArrayDeque<>();
        stateHistory.push(intervention);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public Queue add(Object cargo) {
    	elements.add(cargo);
        intervention = new FEmpty(this);
        stateHistory.push(intervention);
        return this;
    }

    public Object take() {
    	Object item = intervention.take();
    	stateHistory.pop();
    	intervention =  stateHistory.peek();
        return item;
    }

    public Object head() {
        return intervention.head();
    }
   

    public int size() {
        return elements.size();
    }
}
