package campuslostandfound;

public class Item {

    private String ItemName;
    private String description;
    private String location;
    private String dateReported;
    private String status;
public Item(String itemName, String description,
        String location, String dateReported,
        String status) {

    validateRequiredField(itemName, "Item name");
    validateRequiredField(description, "Description");
    validateRequiredField(location, "Location");
    validateRequiredField(dateReported, "Date");
    validateRequiredField(status, "Status");

    this.ItemName = itemName;
    this.description = description;
    this.location = location;
    this.dateReported = dateReported;
    this.status = status;
}
    

    protected final void validateRequiredField(
            String value, String fieldName) {

        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    fieldName + " cannot be empty.");
        }
    }

    public String getItemName() {
        return ItemName;
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

    public String getPersonName() {
        return "N/A";
    }

    public String getItemType() {
        return "Item";
    }

    public void setItemName(String ItemName) {
        validateRequiredField(ItemName, "Item name");
        this.ItemName = ItemName;
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

        if (!status.equalsIgnoreCase("Lost")
                && !status.equalsIgnoreCase("Found")
                && !status.equalsIgnoreCase("Claimed")) {

            throw new IllegalArgumentException(
                    "Invalid status.");
        }

        this.status = status;
    }

    @Override
    public String toString() {
        return getItemType()
                + " | " + ItemName
                + " | " + getPersonName();
    }
}