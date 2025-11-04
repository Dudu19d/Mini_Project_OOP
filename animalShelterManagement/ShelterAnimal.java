package animalShelterManagement;

import animalShelterManagement.model.AdoptionForm;
import animalShelterManagement.model.Bird;
import animalShelterManagement.model.Cat;
import animalShelterManagement.model.Dog;
import animalShelterManagement.model.Volunteer;
import animalShelterManagement.service.Shelter;

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
