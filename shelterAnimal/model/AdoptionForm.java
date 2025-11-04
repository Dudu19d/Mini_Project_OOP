package shelterAnimal.model;

import java.io.Serializable;
import java.time.LocalDate;

public class AdoptionForm implements Serializable {
    private String animalId;
    private String adopterName;
    private String adopterEmail;
    private String adopterPhone;
    private String status;
    private boolean hasYard;
    private boolean hasOtherPets;
    private int hoursHomeAlone;
    private LocalDate submissionDate;

    public AdoptionForm(String animalId, String adopterName, String adopterEmail, String adopterPhone, boolean hasYard, boolean hasOtherPets, int hoursHomeAlone) {
        this.animalId = animalId;
        this.adopterName = adopterName;
        this.adopterEmail = adopterEmail;
        this.adopterPhone = adopterPhone;
        this.hasYard = hasYard;
        this.hasOtherPets = hasOtherPets;
        this.hoursHomeAlone = hoursHomeAlone;
        this.submissionDate = LocalDate.now();
        this.status = "Pending";
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public String getAdopterName() {
        return adopterName;
    }

    public void setAdopterName(String adopterName) {
        this.adopterName = adopterName;
    }

    public String getAdopterEmail() {
        return adopterEmail;
    }

    public void setAdopterEmail(String adopterEmail) {
        this.adopterEmail = adopterEmail;
    }

    public String getAdopterPhone() {
        return adopterPhone;
    }

    public void setAdopterPhone(String adopterPhone) {
        this.adopterPhone = adopterPhone;
    }

    public boolean isHasYard() {
        return hasYard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHasYard(boolean hasYard) {
        this.hasYard = hasYard;
    }

    public boolean isHasOtherPets() {
        return hasOtherPets;
    }

    public void setHasOtherPets(boolean hasOtherPets) {
        this.hasOtherPets = hasOtherPets;
    }

    public int getHoursHomeAlone() {
        return hoursHomeAlone;
    }

    public void setHoursHomeAlone(int hoursHomeAlone) {
        this.hoursHomeAlone = hoursHomeAlone;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public boolean isEligibleFormAdoption(Animal animal){
        if (animal instanceof Dog dog){
            if (dog.getSize().equals("Large") && !hasYard){
                return false;
            }
            return hoursHomeAlone <= 8;
        }else if (animal instanceof Cat cat){
            return !cat.isIndoorOnly() || !hasOtherPets || cat.isGoodWithOtherCats();
        }else if (animal instanceof Bird){
            return hoursHomeAlone <= 6;
        }
        return true;
    }
}
