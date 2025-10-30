package libraryManagement.users;

import libraryManagement.constant.Role;

public class Admin extends User{
    public Admin(String userId, String name, String email){
        super(userId, name, email, Role.ADMIN);
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
        return true;
    }
}
