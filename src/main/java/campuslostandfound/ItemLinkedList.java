
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
            tail.next = newNode;
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
            item item = current.data;

            if (item.getItemName().equalsIgnoreCase(keyword)) {
                return item;
            }

            current = current.next;
        }

        return null;
    }

    // DELETE ITEM USING TABLE ROW INDEX
    public boolean deleteItem(int index) {
        if (index < 0 || head == null) {
            return false;
        }

        if (index == 0) {
            head = head.next;

            if (head == null) {
                tail = null;
            }

            return true;
        }

        Node current = head;

        for (int i = 0; current.next != null && i < index - 1; i++) {
            current = current.next;
        }

        if (current.next == null) {
            return false;
        }

        if (current.next == tail) {
            tail = current;
        }

        current.next = current.next.next;
        return true;
    }

    // UPDATE STATUS USING TABLE ROW INDEX
    public boolean updateStatus(int index, String status) {
        if (index < 0) {
            return false;
        }

        Node current = head;

        for (int i = 0; current != null && i < index; i++) {
            current = current.next;
        }

        if (current == null) {
            return false;
        }

        current.data.setStatus(status);
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
            current = current.next;
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
                return current.data;
            }

            current = current.next;
            count++;
        }

        return null;
    }
}
