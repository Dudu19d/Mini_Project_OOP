package shelterAnimal.model;

public class Bird extends Animal{
    private String species;
    private boolean canTalk;


    public Bird(String id, String name, int age, String species, boolean canTalk) {
        super(id, name, age);
        this.species = species;
        this.canTalk = canTalk;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public boolean isCanTalk() {
        return canTalk;
    }

    public void setCanTalk(boolean canTalk) {
        this.canTalk = canTalk;
    }
    @Override
    public String toString() {
        return super.toString() + String.format("Species: %s, Can talk: %s", species, canTalk ? "Yes" : "No");
    }

    @Override
    public String getType() {
        return "Bird";
    }
}
