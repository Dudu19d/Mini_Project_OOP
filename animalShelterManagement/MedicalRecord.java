package animalShelterManagement;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private List<String> records = new ArrayList<>();
    public void addRecord(String entry) { records.add(entry); }
    public List<String> getRecords() { return records; }
}
