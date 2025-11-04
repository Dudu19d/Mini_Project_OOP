package shelterAnimal;


import shelterAnimal.model.AdoptionForm;
import shelterAnimal.model.Animal;
import shelterAnimal.model.Bird;
import shelterAnimal.model.Cat;
import shelterAnimal.model.Dog;
import shelterAnimal.model.MedicalRecord;
import shelterAnimal.model.Task;
import shelterAnimal.model.Volunteer;
import shelterAnimal.service.ShelterService;

import java.time.LocalDate;
import java.util.Scanner;

public class ApplicationShelter {
    private final ShelterService shelter;
    private final Scanner scanner;

    public ApplicationShelter() {
        this.shelter = new ShelterService("Happy Tails Animal Shelter");
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to the Animal Shelter Management System!");
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1 -> manageAnimals();
                case 2 -> manageAdoptions();
                case 3 -> manageVolunteers();
                case 4 -> manageMedicalRecords();
                case 0 -> {
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void manageAnimals() {
        while (true) {
            System.out.println("=== Manage Animals ===");
            System.out.println("1. Add Animal");
            System.out.println("2. View Available Animals");
            System.out.println("3. View Dogs");
            System.out.println("4. View Cats");
            System.out.println("5. View Birds");
            System.out.println("6. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1 -> addAnimal();
                case 2 -> shelter.displayAvailableAnimals();
                case 3 -> shelter.displayAnimalsByType("Dog");
                case 4 -> shelter.displayAnimalsByType("Cat");
                case 5 -> shelter.displayAnimalsByType("Bird");
                case 6 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    private void manageAdoptions() {
        while (true) {
            System.out.println("=== Manage Adoptions ===");
            System.out.println("1. Submit Adoption Form");
            System.out.println("2. Process Adoption Form");
            System.out.println("3. Return Animal");
            System.out.println("4. Return to Main Menu");

            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1 -> submitAdoptionForm();
                case 2 -> processAdoption();
                case 3 -> returnAnimal();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    private void submitAdoptionForm() {
        shelter.displayAvailableAnimals();
        String animalId = getStringInput("Enter the ID of the animal you want to adopt: ");

        Animal animal = shelter.findAnimalById(animalId);
        if (animal == null || animal.isAdopted()) {
            System.out.println("Animal not found or already adopted.");
            return;
        }
        String adopterName = getStringInput("Enter your name: ");
        String email = getStringInput("Enter your email: ");
        String phone = getStringInput("Enter your phone number: ");
        boolean hasYard = getYesNoInput("Do you have a yard? (yes/no): ");
        boolean hasOtherPets = getYesNoInput("Do you have other pets? (yes/no): ");
        int hoursHomeAlone = getIntInput("How many hours will the animal be home alone? ");

        AdoptionForm form = new AdoptionForm(animalId, adopterName, email, phone, hasYard, hasOtherPets, hoursHomeAlone);
        shelter.submitAdoptionForm(form);
        System.out.println("Adoption form submitted successfully!");
    }

    private void processAdoption() {
        shelter.displayPendingAdoptionForms();
        String formId = getStringInput("Enter the ID of the form you want to adopt: ");
        boolean approved = getYesNoInput("Approve adoption? (yes/no): ");
        shelter.processAdoption(formId, approved);
    }

    private void returnAnimal() {
        String animalId = getStringInput("Enter the ID of the animal to return: ");
        shelter.returnAnimal(animalId);
    }

    private void manageVolunteers() {
        while (true){
            System.out.println("=== Manage Volunteers ===");
            System.out.println("1. Add Volunteer");
            System.out.println("2. View Volunteers");
            System.out.println("3. Assign Volunteer Tasks");
            System.out.println("4. Back to Main Menu");

            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1 -> addVolunteer();
                case 2 -> shelter.displayVolunteers();
                case 3 -> assignTask();
                case 4 -> {
                    return;
                }

                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

    }

    private void addVolunteer(){
        System.out.println("--- Add New Volunteer ---");
        String id = getStringInput("Enter Volunteer ID: ");
        String name = getStringInput("Enter Volunteer Name: ");
        String email = getStringInput("Enter Volunteer Email: ");
        String phone = getStringInput("Enter Volunteer Phone: ");
        shelter.addVolunteer(new Volunteer(id, name, email, phone));
        System.out.println("Volunteer added successfully!");

    }
    private void assignTask() {
        shelter.displayVolunteers();
        String volunteerId = getStringInput("Enter Volunteer ID to assign task: ");
        Volunteer volunteer = shelter.findVolunteerById(volunteerId);
        if (volunteer == null) {
            System.out.println("Volunteer not found.");
            return;
        }

        String taskId = getStringInput("Enter Task ID to assign: ");
        String description = getStringInput("Enter Task Description: ");
        int dayFromNow = getIntInput("Enter number of days from now for the task date: ");

        Task task = new Task(taskId, description, LocalDate.now().plusDays(dayFromNow), volunteerId);
        shelter.addTask(task);

    }

    private void manageMedicalRecords() {
        System.out.println("=== Manage Medical Records ===");
        shelter.displayAvailableAnimals();
        String animalId = getStringInput("Enter the ID of the animal to update medical record: ");
        Animal animal = shelter.findAnimalById(animalId);
        if (animal == null) {
            System.out.println("Animal not found.");
            return;
        }else {
            MedicalRecord medicalRecord = animal.getMedicalRecord();
            System.out.println("Current Medical Record: " + medicalRecord);
            System.out.println("Vaccinations:");
            medicalRecord.getVaccinations().forEach(System.out::println);
            System.out.println("Procedures:");
            medicalRecord.getProcedures().forEach(System.out::println);
        }

    }


    private void addAnimal() {
        System.out.println("--- Add New Animal ---");
        System.out.println("1: Dog");
        System.out.println("2: Cat");
        System.out.println("3: Bird");

        int type = getIntInput("Select animal type: ");
        String id = getStringInput("Enter Animal ID: ");
        String name = getStringInput("Enter Animal Name: ");
        int age = getIntInput("Enter Animal Age: ");
        switch (type) {
            case 1 -> {
                String size = getStringInput("Enter Dog Size (Small/Medium/Large): ");
                boolean goodWithKids = getYesNoInput("Is the dog good with kids? (yes/no): ");
                shelter.addAnimal(new Dog(id, name, age, size, goodWithKids));
            }
            case 2 -> {
                boolean indoorOnly = getYesNoInput("Is the cat talk? (yes/no): ");
                boolean goodWithOtherCats = getYesNoInput("Is the cat good with other cats? (yes/no): ");
                shelter.addAnimal(new Cat(id, name, age, indoorOnly, goodWithOtherCats));
            }
            case 3 -> {
                String species = getStringInput("Enter Bird Species: ");
                boolean canTalk = getYesNoInput("Can the bird fly? (yes/no): ");
                shelter.addAnimal(new Bird(id, name, age, species, canTalk));
            }
            default -> System.out.println("Invalid animal type selected.");
        }
        System.out.println("Animal added successfully!");
    }

    private void displayMenu() {
        System.out.println("=== Animal Shelter Management System ===");
        System.out.println("1. Manage Animals");
        System.out.println("2. Manage Adoption");
        System.out.println("3. Manage Volunteers");
        System.out.println("4. Manage Medical Records");
        System.out.println("0. Exit");
        System.out.print("Select an option: ");
    }

    private int getIntInput(String label) {
        System.out.print(label);
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter a number: ");
            scanner.next();
            System.out.println(label);
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;

    }

    private String getStringInput(String label) {
        System.out.print(label);
        return scanner.nextLine();
    }

    private boolean getYesNoInput(String label) {
        System.out.print(label);
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }

    public static void main(String[] args) {
        ApplicationShelter shelter = new ApplicationShelter();
        shelter.run();
    }
}
