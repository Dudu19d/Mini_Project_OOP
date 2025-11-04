package animalShelterManagement.model;

// Define an interface Adoptable with methods adopt() and returnToShelter().
public interface Adoptable {
    boolean adopt(AdoptionForm form);
    void returnToShelter();
}
