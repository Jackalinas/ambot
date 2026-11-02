
package campuslostandfound;

public class item {
    private String itemName;
    private String description;
    private String location;
    private String dateReported;
    private String status;

    public item(String itemName, String description,
            String location, String dateReported,
            String status) {

        if (itemName == null || itemName.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty.");
        }

        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }

        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty.");
        }

        if (dateReported == null || dateReported.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty.");
        }

        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty.");
        }

        this.itemName = itemName;
        this.description = description;
        this.location = location;
        this.dateReported = dateReported;
        this.status = status;
    }

    // GETTERS
    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getDateReported() {
        return dateReported;
    }

    public String getStatus() {
        return status;
    }

    // SETTERS WITH VALIDATION
    public void setItemName(String itemName) {
        if (itemName == null || itemName.trim().isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty.");
        }
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
        this.description = description;
    }

    public void setLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty.");
        }
        this.location = location;
    }

    public void setDateReported(String dateReported) {
        if (dateReported == null || dateReported.trim().isEmpty()) {
            throw new IllegalArgumentException("Date cannot be empty.");
        }
        this.dateReported = dateReported;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be empty.");
        }
        this.status = status;
    }
}


