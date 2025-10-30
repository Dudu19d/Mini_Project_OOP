package libraryManagement.contract;

public interface ItemReserver {
    boolean reserveItem(String itemId, String userId);
    boolean cancelReservation(String itemId, String userId);

}
