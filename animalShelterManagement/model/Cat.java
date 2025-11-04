package animalShelterManagement.model;

//  Dog, Cat, Bird subclasses with simple adoption conditions
public class Cat extends Animal {
    public Cat(String name, int age) {
        super(name, age);
    }

    public void makeSound() {
        System.out.println("Meow!");
    }

    public String getSpecies() {
        return "Cat";
    }

    @Override
    public boolean adopt(AdoptionForm form) {
        return form.getAdopterAge() >= 18;
    } // Min. age requirement

    @Override
    public void returnToShelter() {
        System.out.println(name + " returned to shelter.");
    }
}
