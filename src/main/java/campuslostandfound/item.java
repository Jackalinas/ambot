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

        validateRequiredField(itemName, "Item name");
        validateRequiredField(description, "Description");
        validateRequiredField(location, "Location");
        validateRequiredField(dateReported, "Date");
        validateRequiredField(status, "Status");

        this.itemName = itemName;
        this.description = description;
        this.location = location;
        this.dateReported = dateReported;
        this.status = status;
    }

    // SHARED VALIDATION METHOD
    protected final void validateRequiredField(
            String value, String fieldName) {

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    fieldName + " cannot be empty.");
        }
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

    // POLYMORPHIC METHODS
    public String getPersonName() {
        return "N/A";
    }

    public String getItemType() {
        return "Item";
    }

    // SETTERS WITH VALIDATION
    public void setItemName(String itemName) {
        validateRequiredField(itemName, "Item name");
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        validateRequiredField(description, "Description");
        this.description = description;
    }

    public void setLocation(String location) {
        validateRequiredField(location, "Location");
        this.location = location;
    }

    public void setDateReported(String dateReported) {
        validateRequiredField(dateReported, "Date");
        this.dateReported = dateReported;
    }

    public void setStatus(String status) {
        validateRequiredField(status, "Status");
        this.status = status;
    }

    // RETURN ITEM INFORMATION USING POLYMORPHISM
    @Override
    public String toString() {
        return getItemType()
                + " | " + itemName
                + " | " + getPersonName();
    }
}