package libraryManagement.model;

import libraryManagement.contract.ItemReserver;
import libraryManagement.contract.ItemSearcher;
import libraryManagement.database.Repository;
import libraryManagement.users.Member;
import libraryManagement.users.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Library implements ItemReserver, ItemSearcher {
    private List<LibraryItem> items;
    private final Map<String, User> users;
    private final Map<String, List<LibraryItem>> borrowItems;
    private final Map<String, LibraryItem> itemMap;

    public Library(){
        this.items = new ArrayList<>();
        this.users = new HashMap<>();
        this.borrowItems = new HashMap<>();
        this.itemMap = new HashMap<>();
        loadFromFiles();
    }
    public void addItem(LibraryItem item){
        this.items.add(item);
        itemMap.put(item.getItemId(), item);
        saveData();
    }
    public void removeItem(String itemId){
        boolean removed = items.removeIf(item -> item.getItemId().equals(itemId));
        if (removed){
            itemMap.remove(itemId);
            saveData();
        }
    }
    public LibraryItem findItemById(String itemId){
        return this.items
                .stream()
                .filter(item -> item.getItemId().replaceAll("\\s", "").toUpperCase().trim().equalsIgnoreCase(itemId.toUpperCase().trim()))
                .findFirst()
                .orElse(null);
    }
    public void addUser(User user){
        users.put(user.getUserId(), user);
        borrowItems.put(user.getUserId(), new ArrayList<>());
        saveData();
    }
    public User findUserById(String userId){
        return users.get(userId.toUpperCase().trim());
    }
    public boolean removeUser(String userId){
        borrowItems.remove(userId);
        boolean removed = users.remove(userId) != null;
        if (removed){
            saveData();
        }
        return removed;
    }

    public void borrowItem(String itemId, String userId){
        LibraryItem item = findItemById(itemId);
        User user = findUserById(userId);
        if (item != null && user != null && user.canBorrowItems() && !item.borrowItem(userId)){
            if (this.borrowItems.get(userId) == null){
                List<LibraryItem> borrowedItems = new ArrayList<>();
                borrowedItems.add(item);
                this.borrowItems.put(userId, borrowedItems);
            }else {
                this.borrowItems.get(userId).add(item);
            }
            if (user instanceof Member){
                ((Member) user).addBorrowItem(item);
            }
            this.itemMap.put(item.getItemId(), item);
        }
        saveData();
    }
    public boolean returnItem(String itemId, String userId){
        LibraryItem item = findItemById(itemId);
        User user = findUserById(userId);

        if (item != null && user != null && item.returnItem()){
            borrowItems.get(userId).remove(item);
            if (user instanceof Member){
                ((Member) user).removeBorrowItem(item);
            }
            saveData();
            return true;
        }
        return false;
    }
    @Override
    public List<LibraryItem> searchByTitle(String title) {
        return this.searchBy(title, LibraryItem::getTitle);
    }
    @Override
    public List<LibraryItem> searchByAuthor(String author) {
        return this.searchBy(author, LibraryItem::getAuthor);
    }
    @Override
    public List<LibraryItem> searchByGenre(String genre) {
        return this.searchBy(genre, LibraryItem::getGenre);
    }
    private List<LibraryItem> searchBy(String keyword, Function<LibraryItem, String> fieldExtractor){
        return items.stream()
                .filter(item -> fieldExtractor.apply(item).toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }
    @Override
    public boolean reserveItem(String itemId, String userId) {
        LibraryItem item = findItemById(itemId);
        boolean reserved = item != null && item.reserveItem(userId);
        if (reserved){
            saveData();
        }
        return reserved;
    }
    @Override
    public boolean cancelReservation(String itemId, String userId) {
        LibraryItem item = findItemById(itemId);
        boolean cancel = item != null && item.cancelReservation(userId);
        if (cancel){
            saveData();
        }
        return cancel;
    }
    public List<LibraryItem> getAllItems(){
        return new ArrayList<>(items);
    }

    public Collection<User> getAllUsers(){
        return users.values();
    }
    public List<LibraryItem> getBorrowedItemsByUser(String userId){
        return borrowItems.getOrDefault(userId, new ArrayList<>());
    }

    private void loadFromFiles() {
        loadItemsFromFile();
        loadUsersFromFile();
        loadBorrowedItemsFromFile();
    }
    private void loadBorrowedItemsFromFile() {
        Map<String, List<String>> borrowdItemsMap = Repository.loadBorrowedItems();

        for (Map.Entry<String, List<String>> entry : borrowdItemsMap.entrySet()){
            String userId = entry.getKey();
            List<LibraryItem> borrowedItems = new ArrayList<>();

            for (String itemId : entry.getValue()){
                LibraryItem item = itemMap.get(itemId);
                if (item != null){
                    borrowedItems.add(item);
                    item.borrowItem(userId);
                }
            }
            borrowItems.put(userId, borrowedItems);
            User user = users.get(userId);
            if (user instanceof Member){
                ((Member) user).initializeBorrowedItems(borrowedItems);
            }
        }
    }
    private void saveData(){
        Repository.saveItems(items);
        Repository.saveUsers(users.values());
        Repository.saveBorrowedItems(getBorrowedItemsMap(), itemMap);
    }
    private Map<String, List<String>> getBorrowedItemsMap(){
        Map<String, List<String>> borrowedItemsMap = new HashMap<>();
        for (Map.Entry<String, List<LibraryItem>> userId : borrowItems.entrySet()){
            List<String> itemsId = new ArrayList<>();
            for (LibraryItem item : userId.getValue()){
                itemsId.add(item.getItemId());
            }
            borrowedItemsMap.put(userId.getKey(), itemsId);
        }
        return borrowedItemsMap;
    }
    private void loadUsersFromFile() {
       List<User> userList = Repository.loadUsers();
       users.clear();
       for (User user : userList) {
           users.put(user.getUserId(), user);
           borrowItems.put(user.getUserId(), new ArrayList<>());
       }
    }
    private void loadItemsFromFile() {
        this.items = Repository.loadItems();
        itemMap.clear();
        for (LibraryItem item : items) {
            itemMap.put(item.getItemId(), item);
        }
    }



}
