package libraryManagement.constant;

public enum Role {
    ADMIN("ADMIN"),
    LIBRARIAN("LIBRARIAN"),
    MEMBER("MEMBER");

    private final String role;

    public String getRole() {
        return role;
    }
    Role(String role) {
        this.role = role;
    }
}
