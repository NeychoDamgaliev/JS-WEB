package fdmc.domain.entities;

/**
 * Created by Neycho Damgaliev on 1/25/2019.
 */
public class Cat {
    private String name;
    private String breed;
    private String color;
    private Integer age;

    public Cat(String name, String breed, String color, Integer age) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.age = age;
    }

    public Cat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
