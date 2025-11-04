package shelterAnimal.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Vaccination implements Serializable {
    private String name;
    private LocalDate date;

    public Vaccination(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
