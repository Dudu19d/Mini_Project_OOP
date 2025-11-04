package shelterAnimal.model;

public class Cat extends Animal{
    private boolean indoorOnly;
    private boolean goodWithOtherCats;
    public Cat(String id, String name, int age, boolean indoorOnly, boolean goodWithOtherCats) {
        super(id, name, age);
        this.indoorOnly = indoorOnly;
        this.goodWithOtherCats = goodWithOtherCats;
    }

    public boolean isIndoorOnly() {
        return indoorOnly;
    }

    public void setIndoorOnly(boolean indoorOnly) {
        this.indoorOnly = indoorOnly;
    }

    public boolean isGoodWithOtherCats() {
        return goodWithOtherCats;
    }

    public void setGoodWithOtherCats(boolean goodWithOtherCats) {
        this.goodWithOtherCats = goodWithOtherCats;
    }
    @Override
    public String toString() {
        return super.toString() + String.format("IndoorOnly:  %s, Good With Other:  %s", indoorOnly, goodWithOtherCats ? "Yes" : "No");
    }

    @Override
    public String getType() {
        return "Cat";
    }
}
