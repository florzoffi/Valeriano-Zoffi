package queue;

public class TEmpty extends QueueIntervention {

    public TEmpty(Queue queue) {
        super(queue);
    }

    public Object take() {
        throw new Error(Queue.QueueEmpty);
    }

    public Object head() {
        throw new Error(Queue.QueueEmpty);
    }
}