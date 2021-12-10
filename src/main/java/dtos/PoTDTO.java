package dtos;

public class PoTDTO {
    private String name;
    private float change;

    public PoTDTO(String name, float change) {
        this.name = name;
        this.change = change;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getChange() {
        return change;
    }

    public void setChange(float change) {
        this.change = change;
    }
}
