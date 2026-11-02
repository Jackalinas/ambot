
package campuslostandfound;

public class FoundItem extends item {
    private String finderName;

    public FoundItem(String name, String description,
                     String location, String date,
                     String status, String finderName) {

        super(name, description, location, date, status);

        if (finderName == null || finderName.trim().isEmpty()) {
            throw new IllegalArgumentException("Finder name cannot be empty.");
        }

        this.finderName = finderName;
    }

    public String getFinderName() {
        return finderName;
    }
}
