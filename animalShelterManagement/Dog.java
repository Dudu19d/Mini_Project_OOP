package animalShelterManagement;

public class Dog extends Animal {
    public Dog(String name, int age) {
        super(name, age);
    }

    public void makeSound() {
        System.out.println("Woof!");
    }

    public String getSpecies() {
        return "Dog";
    }

    @Override
    public boolean adopt(AdoptionForm form) {
        return form.adopterAge >= 18;
    } // Min. age requirement

    @Override
    public void returnToShelter() {
        System.out.println(name + " returned to shelter.");
    }
}
