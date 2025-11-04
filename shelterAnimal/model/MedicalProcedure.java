package shelterAnimal.model;

import java.io.Serializable;
import java.time.LocalDate;

public class MedicalProcedure implements Serializable {
    private String procedureName;
    private LocalDate date;
    private String notes;

    public MedicalProcedure(String procedureName, LocalDate date, String notes) {
        this.procedureName = procedureName;
        this.date = date;
        this.notes = notes;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
