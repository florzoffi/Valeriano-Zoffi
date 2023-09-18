package queue;

public abstract class QueueIntervention {
    protected Queue queue;

    public QueueIntervention(Queue queue) {
        this.queue = queue;
    }
    
    public abstract Object take();
    public abstract Object head();
}