class ShelterApp {
    public static void main(String[] args) {
        Shelter shelter = new Shelter();
        shelter.addAnimal(new Dog("Beethoven", 4));
        shelter.addAnimal(new Cat("Mozart", 2));
        shelter.addAnimal(new Bird("Bach", 1));
        shelter.addVolunteer(new Volunteer("Julie"));

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Shelter Management Menu ---");
            System.out.println("1. List Animals");
            System.out.println("2. Adopt Animal");
            System.out.println("3. Update Medical Record");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch(choice) {
                case 1:
                    shelter.showAnimals();
                    break;
                case 2:
                    System.out.print("Enter animal name to adopt: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter your name: ");
                    String adopterName = scanner.nextLine();

                    int adopterAge;
                    do {
                        System.out.print("Enter your age: ");
                        while (!scanner.hasNextInt()) {
                            System.out.print("Please enter a valid age: ");
                            scanner.next();
                        }
                        adopterAge = scanner.nextInt();
                    } while (adopterAge <= 0);
                    scanner.nextLine(); // consume newline

                    int numCatsAtHome;
                    do {
                        System.out.print("Enter number of cats at your home: ");
                        while (!scanner.hasNextInt()) {
                            System.out.print("Please enter a valid number: ");
                            scanner.next();
                        }
                        numCatsAtHome = scanner.nextInt();
                    } while (numCatsAtHome < 0);
                    scanner.nextLine(); // consume newline

                    AdoptionForm form = new AdoptionForm(adopterName, adopterAge, numCatsAtHome);
                    shelter.adoptAnimal(name, form);
                    break;
                case 3:
                    System.out.print("Enter animal name to update medical record: ");
                    String animalName = scanner.nextLine();
                    System.out.print("Enter medical record detail: ");
                    String record = scanner.nextLine();
                    shelter.updateMedical(animalName, record);
                    break;
                case 4:
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
