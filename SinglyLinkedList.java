import java.util.NoSuchElementException;
import java.util.Objects;

/** A generic singly linked list with push (front insertion) and common operations. */
public class SinglyLinkedList<T> {
    private static final class Node<T> {
        private final T value;
        private Node<T> next;

        private Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /** Inserts a value at the beginning of the list. */
    public void push(T value) {
        Node<T> node = new Node<>(value);
        node.next = head;
        head = node;
        if (tail == null) {
            tail = node;
        }
        size++;
    }

    /** Adds a value to the end of the list. */
    public void append(T value) {
        Node<T> node = new Node<>(value);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /** Removes and returns the first value. */
    public T pop() {
        if (head == null) {
            throw new NoSuchElementException("Cannot pop from an empty list");
        }
        T value = head.value;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return value;
    }

    /** Removes the first matching value, if present. */
    public boolean remove(T value) {
        Node<T> previous = null;
        Node<T> current = head;

        while (current != null) {
            if (Objects.equals(current.value, value)) {
                if (previous == null) {
                    head = current.next;
                } else {
                    previous.next = current.next;
                }
                if (current == tail) {
                    tail = previous;
                }
                size--;
                return true;
            }
            previous = current;
            current = current.next;
        }
        return false;
    }

    public boolean contains(T value) {
        for (Node<T> current = head; current != null; current = current.next) {
            if (Objects.equals(current.value, value)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printList() {
        StringBuilder output = new StringBuilder();
        for (Node<T> current = head; current != null; current = current.next) {
            if (output.length() > 0) {
                output.append(" -> ");
            }
            output.append(current.value);
        }
        System.out.println(output.length() == 0 ? "(empty)" : output);
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.push(20);
        list.push(10);
        list.append(30);

        System.out.print("List: ");
        list.printList();
        System.out.println("Contains 20: " + list.contains(20));
        System.out.println("Removed 20: " + list.remove(20));
        System.out.println("Popped: " + list.pop());
        System.out.print("After updates: ");
        list.printList();
        System.out.println("Size: " + list.size());
    }
}
