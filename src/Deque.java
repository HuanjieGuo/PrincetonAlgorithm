import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        private Item value;
        private Node next;
        private Node last;

        public Node(Item value, Node last, Node next) {
            this.value = value;
            this.next = next;
            this.last = last;
        }
    }

    private Node headNode;
    private Node tailNode;
    private Integer size;

    // construct an empty deque
    public Deque(){
        size = 0;
        headNode = new Node(null, null, null);
        tailNode = new Node(null, null, null);
        headNode.next = tailNode;
        tailNode.last = headNode;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return headNode.next == tailNode;
    }

    // return the number of items on the deque
    public int size(){
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item){
        if (item == null) throw new IllegalArgumentException();
        size++;
        Node addNode = new Node(item, headNode, headNode.next);
        headNode.next.last = addNode;
        headNode.next = addNode;
    }

    // add the item to the back
    public void addLast(Item item){
        if (item == null) throw new IllegalArgumentException();
        size++;
        Node addNode = new Node(item, tailNode.last, tailNode);
        tailNode.last.next = addNode;
        tailNode.last = addNode;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        if (headNode.next == tailNode) throw new NoSuchElementException();
        size --;
        Node removeNode = headNode.next;
        headNode.next.next.last = headNode;
        headNode.next = headNode.next.next;
        removeNode.next = null;
        removeNode.last = null;
        return removeNode.value;
    }

    // remove and return the item from the back
    public Item removeLast(){
        if (headNode.next == tailNode) throw new NoSuchElementException();
        size --;
        Node removeNode = tailNode.last;
        tailNode.last.last.next = tailNode;
        tailNode.last = tailNode.last.last;
        removeNode.last = null;
        removeNode.next = null;
        return removeNode.value;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>{
        Node currentNode;

        DequeIterator(){
            currentNode = headNode;
        }

        @Override
        public boolean hasNext() {
            return currentNode.next != tailNode;
        }

        @Override
        public Item next() {
            if(currentNode.next == tailNode) throw new NoSuchElementException();
            currentNode = currentNode.next;
            return currentNode.value;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void printQueue(){
        StringBuilder printStr = new StringBuilder();
        Node current = headNode.next;
        while (current!=tailNode){
            printStr.append(current.value.toString()).append(" ");
            current = current.next;
        }
        System.out.println(printStr);
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.printQueue();
        deque.addLast(2);
        deque.printQueue();
        deque.addLast(0);
        deque.printQueue();
        Iterator<Integer> iterator = deque.iterator();
        System.out.println("Start Iterate ...");
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("Stop Iterate ...");
        deque.removeFirst();
        deque.printQueue();
        deque.removeLast();
        deque.printQueue();
        deque.removeFirst();
        deque.printQueue();
    }
}