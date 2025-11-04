package animalShelterManagement.model;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecord {
    private final List<String> records;

    public MedicalRecord() {
        records = new ArrayList<>();
    }

    public void addRecord(String entry) {
        records.add(entry);
    }

    public List<String> getRecords() {
        return records;
    }
}
