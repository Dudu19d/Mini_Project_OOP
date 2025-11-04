package animalShelterManagement.model;

public class AdoptionForm {
    private String adopterName;
    private int adopterAge;
    private int numCatsAtHome;

    public AdoptionForm(String adopterName, int adopterAge, int numCatsAtHome) {
        this.adopterName = adopterName;
        this.adopterAge = adopterAge;
        this.numCatsAtHome = numCatsAtHome;
    }

    public String getAdopterName() {
        return adopterName;
    }

    public void setAdopterName(String adopterName) {
        this.adopterName = adopterName;
    }

    public int getAdopterAge() {
        return adopterAge;
    }

    public void setAdopterAge(int adopterAge) {
        this.adopterAge = adopterAge;
    }

    public int getNumCatsAtHome() {
        return numCatsAtHome;
    }

    public void setNumCatsAtHome(int numCatsAtHome) {
        this.numCatsAtHome = numCatsAtHome;
    }
}
