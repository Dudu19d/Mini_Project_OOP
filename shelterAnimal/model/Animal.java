package shelterAnimal.model;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Animal implements Serializable, Adoptable {
    private String id;
    private String name;
    private int age;
    private LocalDate intakeDate;
    private boolean adopted;
    private MedicalRecord medicalRecord;


    public Animal(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.intakeDate = LocalDate.now();
        this.adopted = false;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void setIntakeDate(LocalDate intakeDate) {
        this.intakeDate = intakeDate;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }


    public LocalDate getIntakeDate() {
        return intakeDate;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Age: %d, Intake Date: %s, Adopted: %s", id, name, age, intakeDate, adopted ? "Yes" : "No");
    }

    @Override
    public void adopt() {
        this.adopted = true;
        System.out.println(name + " has been adopted!");
    }

    @Override
    public void returnToShelter() {
        this.adopted = false;
        this.intakeDate = LocalDate.now();
        System.out.println(name + " returned to shelter.");
    }
    public abstract String getType();

}
