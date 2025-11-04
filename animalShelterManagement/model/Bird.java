package animalShelterManagement.model;

public class Bird extends Animal {
    public Bird(String name, int age) {
        super(name, age);
    }

    public void makeSound() {
        System.out.println("Chirp!");
    }

    public String getSpecies() {
        return "Bird";
    }

    @Override
    public boolean adopt(AdoptionForm form) {
        return form.getNumCatsAtHome() <= 1;
    } // Maximum 1 cat authorized to adopt a bird

    @Override
    public void returnToShelter() {
        System.out.println(name + " returned to shelter.");
    }
}
