
package campuslostandfound;

public class Node {
   private item data;
    private Node next;

    public Node(item data) {
        if (data == null) {
            throw new IllegalArgumentException("Node data cannot be null.");
        }

        this.data = data;
        this.next = null;
    }

    // GET ITEM DATA
    public item getData() {
        return data;
    }

    // GET NEXT NODE
    public Node getNext() {
        return next;
    }

    // SET NEXT NODE
    public void setNext(Node next) {
        this.next = next;
    }

    // RETURN ITEM NAME AS NODE DESCRIPTION
    @Override
    public String toString() {
        return data.getItemName();
    }
}
