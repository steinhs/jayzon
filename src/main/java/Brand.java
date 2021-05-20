public class Brand {
    String name;
    int founded;

    public Brand(String name, int founded) {
        this.name = name;
        this.founded = founded;
    }

    public Brand() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFounded() {
        return founded;
    }

    public void setFounded(int founded) {
        this.founded = founded;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "name='" + name + '\'' +
                ", founded=" + founded +
                '}';
    }
}
