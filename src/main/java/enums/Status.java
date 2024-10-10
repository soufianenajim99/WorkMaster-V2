package enums;

public enum Status {
    RECEIVED("received"),
    IN_PROGRESS("in_progress"),
    REJECTED("rejected"),
    ACCEPTED("accepted");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
