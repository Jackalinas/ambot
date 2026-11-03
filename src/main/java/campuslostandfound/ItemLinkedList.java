package campuslostandfound;

public class ItemLinkedList {
  
    private Node head;
    private Node tail;

    public ItemLinkedList() {
        head = null;
        tail = null;
    }

    // ADD ITEM
    public void addItem(Item item) {

        if (item == null) {
            throw new IllegalArgumentException(
                    "Item cannot be null.");
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

    // LINEAR SEARCH
    public Item searchItem(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return null;
        }

        Node current = head;

        while (current != null) {

            Item item = current.getData();

            if (item.getItemName().toLowerCase()
                    .contains(keyword.trim().toLowerCase())) {
                return item;
            }

            current = current.getNext();
        }

        return null;
    }

    // DELETE ITEM
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
                current.getNext() != null && i < index - 1;
                i++) {

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

    // UPDATE STATUS
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

    // GET LAST ITEM
    public Item getLastItem() {

        if (tail == null) {
            return null;
        }

        return tail.getData();
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

    // COUNT ITEMS BY STATUS
    public int countItemsByStatus(String status) {

        if (status == null || status.trim().isEmpty()) {
            return 0;
        }

        int count = 0;
        Node current = head;

        while (current != null) {

            if (current.getData().getStatus()
                    .equalsIgnoreCase(status.trim())) {
                count++;
            }

            current = current.getNext();
        }

        return count;
    }

    // CHECK IF EMPTY
    public boolean isEmpty() {
        return head == null;
    }

    // GET ITEM BY INDEX
    public Item getItemAt(int index) {

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

    // REVERSE LINKED LIST
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