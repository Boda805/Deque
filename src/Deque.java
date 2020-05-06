import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return first == null;
    }

    // return the number of items on the deque
    public int size() {
        int count = 0;
        Iterator<Item> list = iterator();
        while (list.hasNext()) {
            list.next();
            count++;
        }
        return count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException("item cannot be null");

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        first.prev = null;
        if (last == null) last = first;
        else oldfirst.prev = first;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException("item cannot be null");

        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        last.prev = oldlast;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("No elements to remove");

        Item item = first.item;
        first = first.next;
        if (!isEmpty()) first.prev = null;
        return item;

    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("No elements to remove");

        Item item = last.item;
        last = last.prev;
        if (size() == 1) first = last;
        try {
            last.next = null;
        } finally {
            return item;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext())
                throw new java.util.NoSuchElementException("no more elements");

            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}