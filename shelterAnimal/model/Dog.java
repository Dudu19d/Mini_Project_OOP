package shelterAnimal.model;


import java.time.LocalDate;

public class Dog extends Animal{
    private String size;
    private boolean goodWithKids;
    public Dog(String id, String name, int age, String size, boolean goodWithKids) {
        super(id, name, age);
        this.size = size;
        this.goodWithKids = goodWithKids;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public boolean isGoodWithKids() {
        return goodWithKids;
    }

    public void setGoodWithKids(boolean goodWithKids) {
        this.goodWithKids = goodWithKids;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Size:  %s ", size);
    }

    @Override
    public String getType() {
        return "Dog";
    }


}
