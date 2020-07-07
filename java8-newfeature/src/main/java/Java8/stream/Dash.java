package Java8.stream;

/**
 * @Description:
 * @Author: siteFounder
 */
public class Dash {

    private String name;

    private Integer caloes;

    public Dash(String name, Integer caloes) {
        this.name = name;
        this.caloes = caloes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCaloes() {
        return caloes;
    }

    public void setCaloes(Integer caloes) {
        this.caloes = caloes;
    }

    @Override
    public String toString() {
        return "Dash{" +
                "name='" + name + '\'' +
                ", caloes=" + caloes +
                '}';
    }
}
