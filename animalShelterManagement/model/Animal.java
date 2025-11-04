package animalShelterManagement.model;

// Create an abstract class Animal with subclasses Dog, Cat, and Bird

public abstract class Animal implements Adoptable {
    protected String name;
    protected int age;

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    protected MedicalRecord medicalRecord;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        this.medicalRecord = new MedicalRecord();
    }

    public abstract void makeSound();

    public abstract String getSpecies();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
