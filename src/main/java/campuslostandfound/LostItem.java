package campuslostandfound;

 
public class LostItem extends Item{
    private String ownerName;

    public LostItem(String name, String description,
            String location, String date,
            String status, String ownerName) {

        super(name, description, location, date, status);

        validateRequiredField(ownerName, "Owner name");
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String getPersonName() {
        return ownerName;
    }

    @Override
    public String getItemType() {
        return "Lost Item";
    }

    public void setOwnerName(String ownerName) {
        validateRequiredField(ownerName, "Owner name");
        this.ownerName = ownerName;
    }
}      