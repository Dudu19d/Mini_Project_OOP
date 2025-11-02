package animalShelterManagement;

import java.util.ArrayList;
import java.util.List;

// Use loops for displaying available animals and conditions for adoption eligibility with animalShelterManagement.Shelter class with animals and volunteers
public class Shelter {
    private final List<Animal> animals = new ArrayList<>();
    private final List<Volunteer> volunteers = new ArrayList<>();

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
            System.out.println("Adoption not successful");
        }
    }
    public void updateMedical(String animalName, String record) {
        Animal a = findAnimal(animalName);
        if (a != null) a.medicalRecord.addRecord(record);
    }
}
