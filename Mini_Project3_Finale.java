import java.util.*;

// Define an interface Adoptable with methods adopt() and returnToShelter().
interface Adoptable {
    boolean adopt(AdoptionForm form);
    void returnToShelter();
}

// Create an abstract class Animal with subclasses Dog, Cat, and Bird
abstract class Animal implements Adoptable {
    protected String name;
    protected int age;
    protected MedicalRecord medicalRecord;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
        this.medicalRecord = new MedicalRecord();
    }

    public abstract void makeSound();
    public abstract String getSpecies();
    public String getName() { return name; }
    public int getAge() { return age; }
}

//  Dog, Cat, Bird subclasses with simple adoption conditions 
class Dog extends Animal {
    public Dog(String name, int age) { super(name, age); }
    public void makeSound() { System.out.println("Woof!"); }
    public String getSpecies() { return "Dog"; }
    public boolean adopt(AdoptionForm form) { return form.adopterAge >= 18; } // Min. age requirement
    public void returnToShelter() { System.out.println(name + " returned to shelter."); }
}

class Cat extends Animal {
    public Cat(String name, int age) { super(name, age); }
    public void makeSound() { System.out.println("Meow!"); }
    public String getSpecies() { return "Cat"; }
    public boolean adopt(AdoptionForm form) { return form.adopterAge >= 18; } // Min. age requirement
    public void returnToShelter() { System.out.println(name + " returned to shelter."); }
}

class Bird extends Animal {
    public Bird(String name, int age) { super(name, age); }
    public void makeSound() { System.out.println("Chirp!"); }
    public String getSpecies() { return "Bird"; }
    public boolean adopt(AdoptionForm form) { return form.numCatsAtHome <= 1; } // Maximum 1 cat authorized to adopt a bird
    public void returnToShelter() { System.out.println(name + " returned to shelter."); }
}

// Implement enhancement features: Composition, MedicalRecord, Volunteer, AdoptionForm
class MedicalRecord {
    private List<String> records = new ArrayList<>();
    public void addRecord(String entry) { records.add(entry); }
    public List<String> getRecords() { return records; }
}

class AdoptionForm {
    String adopterName;
    int adopterAge;
    int numCatsAtHome;

    public AdoptionForm(String adopterName, int adopterAge, int numCatsAtHome) {
        this.adopterName = adopterName; this.adopterAge = adopterAge; this.numCatsAtHome = numCatsAtHome;
    }
}

class Volunteer {
    String name;
    public Volunteer(String name) { this.name = name; }
}

// Use loops for displaying available animals and conditions for adoption eligibility with Shelter class with animals and volunteers
class Shelter {
    private List<Animal> animals = new ArrayList<>();
    private List<Volunteer> volunteers = new ArrayList<>();

    public void addAnimal(Animal a) { animals.add(a); }
    public void addVolunteer(Volunteer v) { volunteers.add(v); }

    public void showAnimals() {
        System.out.println("--- Animals ---");
        for (Animal a : animals)
            System.out.println(a.getName() + " (" + a.getSpecies() + "), Age: " + a.getAge());
    }
    public Animal findAnimal(String name) {
        for (Animal a : animals)
            if (a.getName().equalsIgnoreCase(name)) return a;
        return null;
    }
    public void adoptAnimal(String animalName, AdoptionForm form) {
        Animal a = findAnimal(animalName);
        if (a != null && a.adopt(form)) {
            animals.remove(a);
            System.out.println(form.adopterName + " adopted " + a.getName() + "!");
        } else {
            System.out.println("Adoption not successful (maybe rule not met or animal not found)");
        }
    }
    public void updateMedical(String animalName, String record) {
        Animal a = findAnimal(animalName);
        if (a != null) a.medicalRecord.addRecord(record);
    }
}

// Dog adoption Example
public class ShelterAnimal {
    public static void main(String[] args) {
        Shelter shelter = new Shelter();
        shelter.addAnimal(new Dog("Beethoven", 4));
        shelter.addAnimal(new Cat("Mozart", 2));
        shelter.addAnimal(new Bird("Bach", 1));
        shelter.addVolunteer(new Volunteer("Julie"));

        shelter.showAnimals();
        shelter.updateMedical("Mozart", "Vaccinated for rabies");

        AdoptionForm form1 = new AdoptionForm("Bob", 25, 0);
        shelter.adoptAnimal("Beethoven", form1); // Success

        AdoptionForm form2 = new AdoptionForm("Carole", 19, 1);
        shelter.adoptAnimal("Beethoven", form2); // Already adopted

        AdoptionForm form3 = new AdoptionForm("Mike", 30, 2);
        shelter.adoptAnimal("Bach", form3); // Should fail because 2 cats at home 

        shelter.showAnimals();
    }

}
