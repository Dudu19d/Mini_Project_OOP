package libraryManagement.contract;

public interface Borrowable {
    Boolean borrowItem(String userId);
    Boolean returnItem();
    Boolean isAvailable();
}
