package libraryManagement.model;

import libraryManagement.contract.Borrowable;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibraryItem implements Borrowable {
    private String title;
    private String author;
    private String genre;
    private String itemId;
    private boolean isBorrowed;
    private LocalDate dueDate;
    private LocalDate borrowDate;
    private List<String> reservations;

    public LibraryItem() {}

    public LibraryItem(String itemId, String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.itemId = itemId;
        this.dueDate = LocalDate.now().plusDays(30);
        this.borrowDate = LocalDate.now();
        this.isBorrowed = false;
        this.reservations = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public List<String> getReservations() {
        return reservations;
    }

    public void setReservations(List<String> reservations) {
        this.reservations = reservations;
    }

    public String toCSV(){
        return String.join(",", itemId,
                title,
                author,
                genre,
                String.valueOf(isBorrowed),
                dueDate != null ? dueDate.toString() : "null",
                borrowDate != null ? borrowDate.toString() : "null",
                String.join(",", reservations));
    }
    public static LibraryItem fromCSV(String line){
        String[] parts = line.split(",");
        String itemId = parts[0];
        String title = parts[1];
        String author = parts[2];
        String genre = parts[3];
        boolean isBorrowed = Boolean.parseBoolean(parts[4]);
        LocalDate dueDate = LocalDate.parse(parts[5]);
        LocalDate borrowDate = LocalDate.parse(parts[6]);
        List<String> reservations;
        if (parts.length == 8){
            reservations = Arrays.asList(parts[7].split(","));
        }else {
            reservations = new ArrayList<>();
        }
        System.out.println("---- parts: " +  Arrays.toString(parts));
        LibraryItem item = new LibraryItem();

        item.setItemId(itemId);
        item.setTitle(title);
        item.setAuthor(author);
        item.setGenre(genre);
        item.setBorrowed(isBorrowed);
        item.setDueDate(dueDate);
        item.setBorrowDate(borrowDate);
        item.setReservations(reservations);
        return item;

    }

    @Override
    public String toString() {
        return String.format("%-8s | %-20s | %-15s | %-10s | %-8s | %-10s | %-10s | %s",
                itemId,
                truncate(title, 18),
                truncate(author, 13),
                truncate(genre, 8),
                isBorrowed ? "Yes" : "No",
                dueDate != null ? dueDate : "N/A",
                borrowDate != null ? borrowDate : "N/A",
                reservations != null && !reservations.isEmpty() ? reservations : "None"
        );
    }
    private String truncate(String str, int maxLength) {
        if (str == null || str.isEmpty()) return "N/A";
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength -3) + "...";

    }


    @Override
    public Boolean borrowItem(String userId) {
        if (!isBorrowed && reservations.isEmpty()){
            this.isBorrowed = true;
            this.dueDate = LocalDate.now().plusDays(30);
            return true;
        }
        return false;
    }

    @Override
    public Boolean returnItem() {
        if (this.isBorrowed){
            this.isBorrowed = false;
            this.dueDate = null;
            return true;
        }
        return null;
    }

    @Override
    public Boolean isAvailable() {
        return !isBorrowed && reservations.isEmpty();
    }
    public boolean reserveItem(String userId) {
        if (isBorrowed && !reservations.contains(userId)){
            reservations.add(userId);
            return true;
        }
        return false;
    }

    public boolean cancelReservation(String userId) {
        return reservations.remove(userId);
    }
}
