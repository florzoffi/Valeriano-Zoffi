package queue;

public class TEmpty extends QueueIntervention {

    public TEmpty(Queue queue) {
        super(queue);
    }

    @Override
    public Object take() {
        throw new Error(queue.QueueEmpty);
    }

    @Override
    public Object head() {
        throw new Error(queue.QueueEmpty);
    }
}
