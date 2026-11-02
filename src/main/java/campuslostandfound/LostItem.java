
package campuslostandfound;

public class LostItem extends item {
    private String ownerName;

    public LostItem(String name, String description,
                    String location, String date,
                    String status, String ownerName) {

        super(name, description, location, date, status);

        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be empty.");
        }

        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    // NEW SETTER METHOD
    public void setOwnerName(String ownerName) {
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be empty.");
        }

        this.ownerName = ownerName;
    }
}
