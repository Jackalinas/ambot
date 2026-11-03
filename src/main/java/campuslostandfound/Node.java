package campuslostandfound;

public class Node {

    Item data;
    Node next;

    public Node(Item data) {
        if (data == null) {
            throw new IllegalArgumentException(
                    "Node data cannot be null.");
        }

        this.data = data;
        this.next = null;
    }

    public Item getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return data.getItemName();
    }
}