package libraryManagement.contract;

import libraryManagement.model.LibraryItem;

import java.util.List;

public interface ItemSearcher {
    List<LibraryItem> searchByTitle(String title);
    List<LibraryItem> searchByAuthor(String author);
    List<LibraryItem> searchByGenre(String category);
}
