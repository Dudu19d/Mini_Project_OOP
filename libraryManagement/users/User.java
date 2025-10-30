package libraryManagement.users;

import libraryManagement.constant.Role;

import java.io.Serializable;

public abstract class User implements Serializable {
    private String userId;
    private String name;
    private String email;
    private Role role;

    public User(String userId, String name, String email, Role role) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Role getRole() {
        return role;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public abstract boolean canBorrowItems();

    public abstract boolean canManageItems();

    public abstract boolean canManageUsers();

    @Override
    public String toString() {
        return String.format("%s: %s (%s)", role, name, email);
    }

    public String toCSV() {
        return String.join(",",
                userId,
                name,
                email,
                role.getRole());
    }

    public static User fromCSV(String line) {
        String[] parts = line.split(",");
        String userId = parts[0];
        String name = parts[1];
        String email = parts[2];
        String role = parts[3];
        return switch (role) {
            case "ADMIN" -> new Admin(userId, name, email);
            case "LIBRARIAN" -> new Librarian(userId, name, email);
            case "MEMBER" -> new Member(userId, name, email);
            default -> null;
        };
    }
}
