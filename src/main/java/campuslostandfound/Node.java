
package campuslostandfound;

public class Node {
    item data;
    Node next;

    public Node(item data) {
        if (data == null) {
            throw new IllegalArgumentException("Node data cannot be null.");
        }

        this.data = data;
        this.next = null;
    }

    // RETURN ITEM NAME AS NODE DESCRIPTION
    @Override
    public String toString() {
        return data.getItemName();
    }
}

