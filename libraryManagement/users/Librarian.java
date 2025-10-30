package libraryManagement.users;

import libraryManagement.constant.Role;

public class Librarian extends User {
    public Librarian(String userId, String name, String email){
        super(userId, name, email, Role.LIBRARIAN);
    }
    @Override
    public boolean canBorrowItems() {
        return true;
    }

    @Override
    public boolean canManageItems() {
        return true;
    }

    @Override
    public boolean canManageUsers() {
        return false;
    }
}
