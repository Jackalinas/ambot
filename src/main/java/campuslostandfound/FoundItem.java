package campuslostandfound;

public class FoundItem extends Item {

    private String finderName;

    public FoundItem(String name, String description,
            String location, String date,
            String status, String finderName) {

        super(name, description, location, date, status);

        validateRequiredField(finderName, "Finder name");
        this.finderName = finderName;
    }

    public String getFinderName() {
        return finderName;
    }

    @Override
    public String getPersonName() {
        return finderName;
    }

    @Override
    public String getItemType() {
        return "Found Item";
    }

    public void setFinderName(String finderName) {
        validateRequiredField(finderName, "Finder name");
        this.finderName = finderName;
    }
}