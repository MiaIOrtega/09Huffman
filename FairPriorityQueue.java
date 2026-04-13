import java.util.LinkedList;

/**
 * Creates a queue in which an element's priority takes
 * precedence.
 */
public class FairPriorityQueue<E extends Comparable<? super E>> {   // I think I'm done??
    private LinkedList<E> queue;
    
    /**
     * Constructs a new FairPriorityQueue.
     */
    public FairPriorityQueue() {
        this.queue = new LinkedList<>();
    }
    
    /**
     * Adds elements to the queue in terms 
     * of priority.
     * 
     * @param element The value to add to the queue.
     */
    public void enqueue(E element) {
        // checks preconditions
        if (element == null) {
            throw new IllegalArgumentException("Violation of precondition: enqueue."
                    + " element must not null.");
        }
        TreeNode node = null;
        if (element instanceof TreeNode) {
            node = (TreeNode) element;
            
            for (int i = 0; i < queue.size(); i++) {
                E val = queue.get(i);
                
                if (val instanceof TreeNode) {
                    TreeNode otherNode = (TreeNode) val;
                    
                    if (node.compareTo(otherNode) < 0) {        // sorts by priority then value (idk if that correct)
                        queue.add(i, element);
                        i = queue.size();
                    } else if (node.compareTo(otherNode) == 0) {
                        if (node.getValue() < otherNode.getValue()) {
                            queue.add(i, element);
                            i = queue.size();
                        }
                    }
                    
//                    if (node.compareTo(otherNode) < 0 || (node.compareTo(otherNode) == 0 &&   // more efficient conditional
//                            node.getValue() < otherNode.getValue())) {
//                        queue.add(i, element);
//                        i = queue.size();
//                    }
                    
//                    if (node.compareTo(otherNode) < 0) {      // implementation that sorts only by frequency
//                        queue.add(i, element);
//                        i = queue.size();
//                    }
                }
            }  
        }
        
        if (isEmpty() || !queue.contains(element)) {
            queue.add(element);
        }
    }
    
    /**
     * Removes the element with the 
     * highest priority from the queue.
     * 
     * @return The element with the highest priority
     * is returned.
     */
    public E dequeue() {
        return queue.remove();
    }
    
    /**
     * Retrieves the element with the highest
     * priority (the front) of the queue.
     * 
     * @return The element with the highest priority
     * is returned.
     */
    public E front() {
        return queue.peek();
    }
    
    /**
     * Determines whether or not this queue
     * is empty.
     * 
     * @return True if the queue is empty,
     * false otherwise.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    /**
     * Determines the size of this
     * FairPriorityQueue.
     * 
     * @return The number of elements in the
     * queue is returned.
     */
    public int size() {     // don't know about this method
        return queue.size();
    }
}