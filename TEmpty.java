package queue;

public class TEmpty extends QueueIntervention {

    public TEmpty(Queue queue) {
        super(queue);
    }

    @Override
    public Object take() {
        throw new Error(Queue.QueueEmpty);
    }

    @Override
    public Object head() {
        throw new Error(Queue.QueueEmpty);
    }
}
