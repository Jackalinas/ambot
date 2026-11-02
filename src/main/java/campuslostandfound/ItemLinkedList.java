


package campuslostandfound;

public class ItemLinkedList {
    private Node head;

    public ItemLinkedList() {
        head = null;
    }

    // ADD ITEM
    public void addItem(item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }

        Node newNode = new Node(item);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    // MANUAL LINEAR SEARCH BY ITEM NAME
    public item searchitem(String keyword) {
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
            return true;
        }

        Node current = head;

        for (int i = 0; current.next != null && i < index - 1; i++) {
            current = current.next;
        }

        if (current.next == null) {
            return false;
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

    // COUNT TOTAL ITEMS IN THE LINKED LIST
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
}

