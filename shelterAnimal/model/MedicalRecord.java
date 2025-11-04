package shelterAnimal.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class MedicalRecord implements Serializable {
    private String animalId;
    private List<Vaccination> vaccinations;
    private final List<MedicalProcedure> procedures;

    public MedicalRecord(String animalId, List<Vaccination> vaccinations, List<MedicalProcedure> procedures) {
        this.animalId = animalId;
        this.vaccinations = vaccinations;
        this.procedures = procedures;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public List<Vaccination> getVaccinations() {
        return vaccinations;
    }

    public void setVaccinations(List<Vaccination> vaccinations) {
        this.vaccinations = vaccinations;
    }

    public List<MedicalProcedure> getProcedures() {
        return procedures;
    }

    public void addVaccination(String vaccine, LocalDate date) {
        this.vaccinations.add(new Vaccination(vaccine, date));
    }
    public void addMedicalProcedure(String procedure, LocalDate date, String note) {
        this.procedures.add(new MedicalProcedure(procedure, date, note));
    }
}
