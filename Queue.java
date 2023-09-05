package queue;

import java.util.ArrayList;
import java.util.List;

public class Queue<T> {
    private List<T> elements;
    public static String QueueEmpty = "Queue is empty";

    public Queue() {
        elements = new ArrayList<>();
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public Queue<T> add(T cargo) {
        if (elements.contains(cargo)) {
            throw new Error("Duplicate element not allowed");
        }

        elements.add(cargo);
        return this;
    }

    public T take() {
        if (isEmpty()) {
            throw new Error(QueueEmpty);
        }

        T cargo = elements.remove(0);
        return cargo;
    }

    public T head() {
        try {
            return elements.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new Error(QueueEmpty);
        }
    }


    public int size() {
        return elements.size();
    }
}
