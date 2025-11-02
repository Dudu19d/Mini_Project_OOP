package libraryManagement.database;

import libraryManagement.model.LibraryItem;
import libraryManagement.users.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static libraryManagement.constant.Constant.BORROWED_ITEMS_FILE;
import static libraryManagement.constant.Constant.ITEMS_FILE;
import static libraryManagement.constant.Constant.USERS_FILE;

public class Repository {

    public static boolean saveItems(List<LibraryItem> items){
        try (PrintWriter writer = new PrintWriter(new FileWriter(ITEMS_FILE))){
            items.forEach(item -> {
                writer.println(item.toCSV());
            });
            return true;
        }catch (IOException e){
            System.err.println("Error saving library items to file");
            return false;
        }
    }
    public static List<LibraryItem> loadItems(){
        List<LibraryItem> items = new ArrayList<>();
        File file = new File(ITEMS_FILE);
        if (!file.exists()){
            return items;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEMS_FILE))){
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    LibraryItem item = LibraryItem.fromCSV(line);
                    items.add(item);

                }
            }

        } catch(IOException e){
            System.err.println("Error saving library items to file");
        }
        return items;
    }
    public static void saveUsers(Collection<User> users) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE))) {
            users.forEach(user -> writer.println(user.toCSV()));
        }catch (IOException e){
            System.err.println("Error saving users to file");
        }

    }
    public static List<User> loadUsers(){
        List<User> users = new ArrayList<>();
        File file = new File(USERS_FILE);
        if (!file.exists()){
            return users;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null){
                if (!line.trim().isEmpty()){
                    User user = User.fromCSV(line);
                    users.add(user);
                }
            }
        }catch (IOException e){
            System.err.println("Error saving users to file");
        }
        return users;
    }
    public static void saveBorrowedItems(Map<String, List<String>> borrowedItemsMap, Map<String, LibraryItem> itemMap) {
        try(PrintWriter writer = new PrintWriter(new FileWriter(BORROWED_ITEMS_FILE))) {
            for (Map.Entry<String, List<String>> entry : borrowedItemsMap.entrySet()){
                for (String itemId : entry.getValue()){
                    LibraryItem item = itemMap.get(itemId);
                    if (item != null){
                        item.getReservations().add(entry.getKey());
                        System.out.println("--reservation: " + item.getReservations());
                        writer.println(item.toCSV());
                    }
                }
            }
            writer.flush();
        }catch (IOException e){
            System.err.println("Error saving borrowed items to file");
        }
    }
    public static Map<String, List<String>> loadBorrowedItems(){
        Map<String, List<String>> borrowedItemsMap = new HashMap<>();
        File file = new File(BORROWED_ITEMS_FILE);
        if (!file.exists()){
            return borrowedItemsMap;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(BORROWED_ITEMS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null){
                if (!line.trim().isEmpty()){
                    String[] parts = line.split(",");
                    if (parts.length >= 2){
                        String userId = parts[0];
                        String itemId = parts[1];
                        borrowedItemsMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(itemId);
                    }
                }
            }

        }catch (IOException e){
            System.err.println("Error saving borrowed items to file");
        }
        return borrowedItemsMap;
    }

}
