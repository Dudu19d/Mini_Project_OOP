package libraryManagement;

import libraryManagement.constant.Role;
import libraryManagement.model.Library;
import libraryManagement.model.LibraryItem;
import libraryManagement.users.Admin;
import libraryManagement.users.Librarian;
import libraryManagement.users.Member;
import libraryManagement.users.User;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Application {
    public static void main(String[] args) {

        Library library = new Library();
        if (library.getAllItems().isEmpty()){
            initializeSampleData(library);
        }


        showMenu();
        Scanner scanner = new Scanner(System.in);
        int options =  scanner.nextInt();
        while (options != 0) {
            scanner.nextLine();
            switch (options) {
                case 1 -> addItem(library, scanner);
                case 2 -> removeItem(library, scanner);
                case 3 -> findItem(library, scanner);
                case 4 -> showAll(library);
                case 5 -> searchByTitle(library, scanner);
                case 6 -> searchByAuthor(library, scanner);
                case 7 -> searchByGenre(library, scanner);
                case 8 -> createUser(library, scanner);
                case 9 -> borrowItem(library, scanner);
            }
            showMenu();
            options = scanner.nextInt();
        }
        scanner.close();
    }

    private static void borrowItem(Library library, Scanner scanner) {
        System.out.print("Enter the user Id: ");
        String userId = scanner.nextLine();
        User user = library.findUserById(userId);
        if (user == null){
            System.out.println("User not found");
        }else {
            System.out.print("Enter the item Id: ");
            String itemId = scanner.nextLine();
            LibraryItem item = library.findItemById(itemId);
            if (item == null){
                System.out.println("Item not found");
            }else {
                System.out.println("The following item has been found: ");
                printHeader();
                System.out.println(item);
                library.borrowItem(itemId, userId);
            }
        }
        System.out.println("Enter the item id: ");
    }

    private static void createUser(Library library, Scanner scanner) {
        System.out.print("Enter your user Id: ");
        String userId = scanner.nextLine();
        User user = library.findUserById(userId);
        if (user == null){
            System.out.println("Invalid user Id");
        }else if (user.getRole() != Role.ADMIN){
            System.out.println(user.getRole());
            System.out.println("You do not have privileges to perform this operation");
        }else {
            System.out.print("Select user user role: 1= Admin, 2= Librarian, 3= Member: ");
            int roleOption = scanner.nextInt();
            scanner.nextLine();
            switch (roleOption){
                case 1 -> {
                    System.out.print("Enter the name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the email: ");
                    String email = scanner.nextLine();
                    User admin = new Admin(getUserId(library), name, email);
                    library.addUser(admin);
                }
                case 2 -> {
                    System.out.print("Enter the name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the email: ");
                    String email = scanner.nextLine();
                    User librarian = new Librarian(getUserId(library), name, email);
                    library.addUser(librarian);
                }
                case 3 -> {
                    System.out.print("Enter the name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the email: ");
                    String email = scanner.nextLine();
                    User librarian = new Member(getUserId(library), name, email);
                    library.addUser(librarian);
                }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private static void searchByGenre(Library library, Scanner scanner) {
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        List<LibraryItem> items = library.searchByGenre(genre);
        if (items.isEmpty()){
            System.out.printf("No items with genre %s was found\n", genre);
        }else {
            printHeader();
            items.forEach(System.out::println);
        }

    }

    private static void searchByAuthor(Library library, Scanner scanner) {
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        List<LibraryItem> items = library.searchByAuthor(author);
        if (items.isEmpty()){
            System.out.printf("No item with author %s not found\n", author);
        }else {
            printHeader();
            items.forEach(System.out::println);
        }

    }

    private static void searchByTitle(Library library, Scanner scanner) {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        List<LibraryItem> items = library.searchByTitle(title);
        if (items.isEmpty()){
            System.out.printf("No item with title %s was found\n", title);
        }else {
            printHeader();
            items.forEach(System.out::println);
        }
    }

    private static void showAll(Library library) {
        List<LibraryItem> items = library.getAllItems();
        if (items.isEmpty()){
            System.out.println("No items found");
        }else {
            printHeader();
            items.forEach(System.out::println);
        }
    }

    private static void findItem(Library library, Scanner scanner) {
        System.out.print("Enter the boot's ID: ");
        String bootId = scanner.nextLine();
        LibraryItem item = library.findItemById(bootId);
        if (item == null){
            System.out.println("Nothing was find with id: "  + bootId);
        }else {
            printHeader();
            System.out.println(item);
        }
    }

    private static void showMenu(){
        System.out.println("1: Add Item in a Database: ");
        System.out.println("2: Remove Item in a Database: ");
        System.out.println("3: Find Item in a Database: ");
        System.out.println("4: Show All Items in a Database: ");
        System.out.println("5: Search by title: ");
        System.out.println("6: Search by author: ");
        System.out.println("7: Search by genre: ");
        System.out.println("8: Add user: ");
        System.out.println("9: Borrow Item: ");
        System.out.println("0: Exit");
        System.out.print("Enter your selection: ");
    }
    public static void printHeader(){
        System.out.printf("%-8s | %-20s | %-15s | %-10s | %-8s | %-10s | %-10s | %s%n",
                "ID", "TITLE", "AUTHOR", "GENRE", "STATUS", "DUE DATE", "BORROW DATE", "RESERVATIONS"
        );
        System.out.println("-".repeat(140));
    }
    private static void addItem(Library library, Scanner scanner){
        System.out.print("Enter book's title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book's author: ");
        String author = scanner.nextLine();
        System.out.print("Enter the book's genre: ");
        String genre = scanner.nextLine();
        LibraryItem book = new LibraryItem(getBookId(library), title, author, genre);
        library.addItem(book);
    }
    private static void removeItem(Library library, Scanner scanner){
        System.out.print("Enter boot's id: ");
        String id = scanner.nextLine();
        if (!id.isEmpty()) {
            LibraryItem item = library.findItemById(id);
            if (item == null) {
                System.out.println("No item with that id exists");
            } else {
                library.removeItem(id);
            }
        }
    }
    private static String getBookId(Library library){
        Set<Integer> allItems = library.getAllItems()
                .stream()
                .map(LibraryItem::getItemId)
                .map(id -> id.replaceAll("\\D", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        int max = Collections.max(allItems);
        return "B00" + (max+1);
    }
    private static String getUserId(Library library){
        Set<Integer> allItems = library.getAllUsers()
                .stream()
                .map(User::getUserId)
                .map(id -> id.replaceAll("\\D", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
        int max = Collections.max(allItems);
        return "U00" + (max+1);
    }
    private static void initializeSampleData(Library library){
        System.out.println("Initializing with simple data...");

        library.addItem(new LibraryItem("B001","The Great Gatsby", "F. Scott Fitzgerald", "Fiction"));
        library.addItem(new LibraryItem("B002","To Kill a Mockingbird", "Harper Lee", "Fiction"));
        library.addItem(new LibraryItem("B003","National Geographic", "Various", "Science"));


        // Add sample users
        library.addUser(new Member("U001", "Alice Johnson", "alice@email.com"));
        library.addUser(new Librarian("U002", "Bob Smith", "bob@library.com"));
        library.addUser(new Admin("U003", "Carol Davis", "carol@library.com"));

        // Demonstrate some borrow operations
        library.borrowItem("B001", "MEM001");
    }
    private static void demoSystem(Library library){
        System.out.println("........................ LIBRARY MANAGEMENT SYSTEM ......................");
        System.out.println("Items in library: " +  library.getAllItems().size());
        System.out.println("Users registered: "+ library.getAllUsers().size());

        System.out.println("......All Items...");
        library.getAllItems().forEach(item -> System.out.println(item + " - Availability: " + item.isAvailable()));
        System.out.println("......All Borrowed Items...");
        for (User user : library.getAllUsers()) {
            List<LibraryItem> borrowed = library.getBorrowedItemsByUser(user.getUserId());
            if (!borrowed.isEmpty()){
                System.out.println(user.getName() + "'s borrowed items:") ;
                borrowed.forEach(item -> System.out.println(" - " + item));
            }
        }
    }

}
