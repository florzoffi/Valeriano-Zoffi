package queue;

public class FEmpty extends QueueIntervention {

    public FEmpty(Queue queue) {
        super(queue);
    }
    
    public Object take() {
        return queue.elements.remove(0);
    }

    public Object head() {
        return queue.elements.get(0);
    }
}