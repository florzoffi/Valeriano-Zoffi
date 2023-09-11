package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue {
    public List<Object> elements;
    public static String QueueEmpty = "Queue is empty";
    private QueueIntervention intervention;

    public Queue() {
        elements = new ArrayList<>();
        intervention = new TEmpty(this);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public Queue add(Object cargo) {
        if (elements.contains(cargo)) {
            throw new Error("Duplicate element not allowed");
        }

        elements.add(cargo);
        intervention = new FEmpty(this);
        return this;
    }

    public Object take() {
    	if (size()<= 0) {
    		intervention = new TEmpty(this);
    	}
        return intervention.take();
    }

    public Object head() {
        return intervention.head();
    }

    public int size() {
        return elements.size();
    }
}