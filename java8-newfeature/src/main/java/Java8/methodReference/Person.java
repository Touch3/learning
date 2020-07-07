package Java8.methodReference;

/**
 * @Description:
 * @Author: siteFounder
 */
public class Person {

    private String weight;
    private String height;

    public Person(String weight, String height) {
        this.weight = weight;
        this.height = height;
    }

    public Person() {

    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "weight='" + weight + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
