package queue;

public class FEmpty extends QueueIntervention {

    public FEmpty(Queue queue) {
        super(queue);
    }
    
    @Override
    public Object take() {
        Object cargo = queue.elements.remove(0);
        return cargo;
    }

    @Override
    public Object head() {
        return queue.elements.get(0);
    }
}