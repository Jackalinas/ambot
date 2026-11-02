
package campuslostandfound;

public class ItemLinkedList {
    private Node head;
    private Node tail;

    public ItemLinkedList() {
        head = null;
        tail = null;
    }

    // ADD ITEM USING TAIL POINTER
    public void addItem(item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }

        Node newNode = new Node(item);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    // MANUAL LINEAR SEARCH BY ITEM NAME
    public item searchitem(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return null;
        }

        Node current = head;

        while (current != null) {
            item item = current.getData();

            if (item.getItemName().equalsIgnoreCase(keyword)) {
                return item;
            }

            current = current.getNext();
        }

        return null;
    }

    // DELETE ITEM USING TABLE ROW INDEX
    public boolean deleteItem(int index) {
        if (index < 0 || head == null) {
            return false;
        }

        if (index == 0) {
            head = head.getNext();

            if (head == null) {
                tail = null;
            }

            return true;
        }

        Node current = head;

        for (int i = 0;
                current.getNext() != null && i < index - 1; i++) {
            current = current.getNext();
        }

        if (current.getNext() == null) {
            return false;
        }

        if (current.getNext() == tail) {
            tail = current;
        }

        current.setNext(current.getNext().getNext());
        return true;
    }

    // UPDATE STATUS USING TABLE ROW INDEX
    public boolean updateStatus(int index, String status) {
        if (index < 0) {
            return false;
        }

        Node current = head;

        for (int i = 0; current != null && i < index; i++) {
            current = current.getNext();
        }

        if (current == null) {
            return false;
        }

        current.getData().setStatus(status);
        return true;
    }

    // GET FIRST NODE
    public Node getHead() {
        return head;
    }

    // COUNT TOTAL ITEMS
    public int getSize() {
        int count = 0;
        Node current = head;

        while (current != null) {
            count++;
            current = current.getNext();
        }

        return count;
    }

    // CHECK IF LINKED LIST IS EMPTY
    public boolean isEmpty() {
        return head == null;
    }

    // GET ITEM BY INDEX
    public item getItemAt(int index) {
        if (index < 0) {
            return null;
        }

        Node current = head;
        int count = 0;

        while (current != null) {
            if (count == index) {
                return current.getData();
            }

            current = current.getNext();
            count++;
        }

        return null;
    }

    // REVERSE THE LINKED LIST
    public void reverseList() {
        Node previous = null;
        Node current = head;
        Node nextNode;

        tail = head;

        while (current != null) {
            nextNode = current.getNext();
            current.setNext(previous);
            previous = current;
            current = nextNode;
        }

        head = previous;
    }
}
