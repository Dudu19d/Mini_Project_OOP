package shelterAnimal.service;

import shelterAnimal.model.AdoptionForm;
import shelterAnimal.model.Animal;
import shelterAnimal.model.Bird;
import shelterAnimal.model.Cat;
import shelterAnimal.model.Dog;
import shelterAnimal.model.Task;
import shelterAnimal.model.Volunteer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static shelterAnimal.service.Constant.ANIMALS_FILE;
import static shelterAnimal.service.Constant.FORMS_FILE;
import static shelterAnimal.service.Constant.TASKS_FILE;
import static shelterAnimal.service.Constant.VOLUNTEERS_FILE;

public class ShelterService {
    private List<Animal> animals;
    private List<Volunteer> volunteers;
    private List<AdoptionForm> adoptionForms;
    private List<Task> tasks;
    private String shelterName;



    public ShelterService(String shelterName) {
        this.animals = new ArrayList<>();
        this.volunteers = new  ArrayList<>();
        this.adoptionForms = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.shelterName = shelterName;

        loadData();
    }
    @SuppressWarnings("unchecked")
    private void loadData() {
        // Implement data loading from files
        // Example: this.animals = FileService.loadAnimals(ANIMALS_FILE);
        try {
            File animalFile = new File(ANIMALS_FILE);
            if (animalFile.exists()){
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(animalFile));
                animals = (List<Animal>) objectInputStream.readObject();
                objectInputStream.close();
            }
            File volunteersFile = new File(VOLUNTEERS_FILE);
            if (volunteersFile.exists()){
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(volunteersFile));
                volunteers = (List<Volunteer>) objectInputStream.readObject();
                objectInputStream.close();
            }
            File formsFile = new File(FORMS_FILE);
            if (formsFile.exists()){
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(formsFile));
                adoptionForms = (List<AdoptionForm>) objectInputStream.readObject();
                objectInputStream.close();
            }
            File tasksFile = new File(TASKS_FILE);
            if (tasksFile.exists()){
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(tasksFile));
                tasks = (List<Task>) objectInputStream.readObject();
                objectInputStream.close();
            }
        }catch (IOException | ClassNotFoundException e){
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
    private void saveData(){
        try {
            // save animals
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new java.io.FileOutputStream(ANIMALS_FILE));
            saveAnimals();
            objectOutputStream.close();

            // save volunteers
            objectOutputStream = new ObjectOutputStream(new java.io.FileOutputStream(VOLUNTEERS_FILE));
            objectOutputStream.writeObject(volunteers);
            objectOutputStream.close();

            // save adoption forms
            objectOutputStream = new ObjectOutputStream(new java.io.FileOutputStream(FORMS_FILE));
            objectOutputStream.writeObject(adoptionForms);
            objectOutputStream.close();

            // save tasks
            objectOutputStream = new ObjectOutputStream(new java.io.FileOutputStream(TASKS_FILE));
            objectOutputStream.writeObject(tasks);
            objectOutputStream.close();
        }catch (IOException e){
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    private void saveAnimals(){
        try(PrintWriter writer = new PrintWriter(new FileWriter(ANIMALS_FILE))){
            for (Animal animal : animals){
                if (animal instanceof Dog dog){
                    writer.printf("DOG: %s,%s,%d,%s,%b,%b%n", dog.getId(), dog.getName(), dog.getAge(), dog.getSize(), dog.isGoodWithKids(), dog.isAdopted());
                }
                if (animal instanceof Cat cat){
                    writer.printf("CAT: %s,%s,%d,%s,%b,%b%n", cat.getId(), cat.getName(), cat.getAge(), cat.isAdopted() ? "Yes" : "No", cat.isIndoorOnly() ? "Yes" : "No", cat.isGoodWithOtherCats() ? "Yes" : "No");
                }
                if (animal instanceof Bird bird){
                    writer.printf("BIRD: %s,%s,%d,%s,%b,%b%n", bird.getId(), bird.getName(), bird.getAge(), bird.getSpecies(), bird.isCanTalk() ? "Yes" : "No", bird.isAdopted() ? "Yes" : "No");
                }
            }
        }catch (IOException e){
            System.out.println("Error saving animals: " + e.getMessage());
        }
    }
    public void addAnimal(Animal animal){
        animals.add(animal);
        saveData();
    }
    public List<Animal> getAvailableAnimals(){
     return animals.stream()
             .filter(animal -> !animal.isAdopted())
             .collect(Collectors.toList());
    }
    public Animal findAnimalById(String id){
        return animals.stream()
                .filter(animal -> animal.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    //Volunteer Management
    public void addVolunteer(Volunteer volunteer){
        volunteers.add(volunteer);
        saveData();
    }
    public void addTask(Task task){
        tasks.add(task);
        saveData();
    }
    public Volunteer findVolunteerById(String id){
        return volunteers.stream()
                .filter(volunteer -> volunteer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    //Adoption Form Management
    public void addAdoptionForm(AdoptionForm form){
        adoptionForms.add(form);
        saveData();
    }
    public List<AdoptionForm> getAdoptionForms(){
        return adoptionForms;

    }
    //display methods with loops
    public void displayAvailableAnimals(){
        List<Animal> availableAnimals = getAvailableAnimals();
        if (availableAnimals.isEmpty()) {
            System.out.println("No available animals for adoption.");
        } else {
            System.out.println("Available Animals for Adoption:");
            animals.forEach(System.out::println);
        }
    }
    public void displayAnimalsByType(String type){
        List<Animal> filteredAnimals = animals.stream()
                .filter(animal -> animal.getType().equalsIgnoreCase(type) && !animal.isAdopted())
                .toList();
        if (filteredAnimals.isEmpty()) {
            System.out.println("No available " + type + "s for adoption.");
        } else {
            System.out.println("Available " + type + "s for Adoption:");
            filteredAnimals.forEach(System.out::println);
        }
    }
    public void displayVolunteers(){
        if (volunteers.isEmpty()) {
            System.out.println("No volunteers found.");
        } else {
            System.out.println("Volunteers:");
            volunteers.forEach(System.out::println);
        }
    }
    public void processAdoption(String animalId, boolean approved){
        AdoptionForm form = adoptionForms.stream()
                .filter(f -> f.getAnimalId().equals(animalId))
                .findFirst()
                .orElse(null);
        if (form == null) {
            System.out.println("Adoption form not found.");
            return;
        }
        Animal animal = findAnimalById(animalId);
        if (animal == null) {
            System.out.println("Animal not found.");
            return;
        }
        if (approved) {
            animal.setAdopted(true);
            System.out.println("Adoption approved for " + animal.getName() + ".");
        } else {
            System.out.println("Adoption denied for " + animal.getName() + ".");
        }
        saveData();
    }
    public void returnAnimal(String animalId){
        Animal animal = findAnimalById(animalId);
        if (animal == null) {
            System.out.println("Animal not found.");
            return;
        }
        animal.setAdopted(false);
        animal.returnToShelter();
        System.out.println("Animal " + animal.getName() + " has been returned to the shelter.");
        saveData();
    }
    public void submitAdoptionForm(AdoptionForm form){
        adoptionForms.add(form);
        saveData();
    }
    public void displayPendingAdoptionForms(){
        List<AdoptionForm> pendingForms = adoptionForms.stream()
                .filter(form -> form.getStatus().equalsIgnoreCase("Pending"))
                .toList();
        if (pendingForms.isEmpty()) {
            System.out.println("No pending adoption forms.");
        } else {
            System.out.println("Pending Adoption Forms:");
            pendingForms.forEach(System.out::println);
        }
    }
    public void displayTasks(){
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("Tasks:");
            tasks.forEach(System.out::println);
        }
    }

    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }
}
