package libraryManagement.users;

import libraryManagement.model.LibraryItem;
import libraryManagement.constant.Role;

import java.util.ArrayList;
import java.util.List;

public class Member extends User {
    private List<LibraryItem> borrowItems;
    public Member(String userId, String name, String email){
        super(userId, name, email, Role.MEMBER);
        this.borrowItems = new ArrayList<>();
    }
    @Override
    public boolean canBorrowItems() {
        return true;
    }

    @Override
    public boolean canManageItems() {
        return false;
    }

    @Override
    public boolean canManageUsers() {
        return false;
    }
    public List<LibraryItem> getBorrowItems() {
        return borrowItems;
    }
    public void addBorrowItem(LibraryItem item){
        this.borrowItems.add(item);
    }
    public void removeBorrowItem(LibraryItem item){
        this.borrowItems.remove(item);
    }

    public void initializeBorrowedItems(List<LibraryItem> borrowedItems) {
        this.borrowItems = borrowedItems;
    }
}
