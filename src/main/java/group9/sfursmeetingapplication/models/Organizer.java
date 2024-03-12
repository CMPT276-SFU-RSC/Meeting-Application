package group9.sfursmeetingapplication.models;




public class Organizer extends User {
    private String additionalField;

    public Organizer() {
        // Default constructor
    }

    public Organizer(Long uid, String email, String password, String firstName, String lastName, String team,
                        String title, boolean isOrganizer, boolean isEnabled, String additionalField) {
        super(uid, email, password, firstName, lastName, team, title, isOrganizer, isEnabled);

        // Check the condition
        if (!isOrganizer) {
            throw new IllegalArgumentException("isOrganizer must be true for Organizer");
        }

        this.additionalField = additionalField;
    }

    public String getAdditionalField() {
        return additionalField;
    }

    public void setAdditionalField(String additionalField) {
        this.additionalField = additionalField;
    }
}
