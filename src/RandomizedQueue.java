import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

//ARRAY

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Integer size;
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue(){
        size = 0;
        items = (Item[]) new Object[16];
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){
        if(item == null) throw new IllegalArgumentException();
        if(size == items.length){
            Item[] longerItems =  (Item[]) new Object[size*2];
            for(int i=0;i<size;i++)
                longerItems[i] = items[i];
            items = longerItems;
        }
        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue(){
        if(size==0) throw new NoSuchElementException();
        int randIdx = StdRandom.uniformInt(size);
        Item popItem = items[randIdx];
        items[randIdx] = items[size-1];
        size--;
        if(size < items.length/4){
            Item[] shorterItems = (Item[]) new Object[items.length/2];
            for(int i=0;i<size;i++)
                shorterItems[i] = items[i];
            items = shorterItems;
        }
        return popItem;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        if(size==0) throw new NoSuchElementException();
        return items[StdRandom.uniformInt(size)];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item>{
        int[] iterateIdx;
        int currentIdx;

        RandomIterator(){
            iterateIdx = StdRandom.permutation(size);
            currentIdx = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIdx < iterateIdx.length;
        }

        @Override
        public Item next() {
            if(currentIdx == iterateIdx.length) throw new NoSuchElementException();
            return items[iterateIdx[currentIdx++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args){
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.sample());
        System.out.println(queue.sample());
        System.out.println(queue.sample());
        Iterator<Integer> iterator1 = queue.iterator();
        Iterator<Integer> iterator2 = queue.iterator();
        StringBuilder builder1 = new StringBuilder();
        StringBuilder builder2 = new StringBuilder();
        while (iterator1.hasNext())
            builder1.append(iterator1.next()).append(" ");
        while (iterator2.hasNext())
            builder2.append(iterator2.next()).append(" ");
        System.out.println("random1: " + builder1);
        System.out.println("random2: " + builder2);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }

}