package model;

import java.util.List;

public class City extends BaseModel{
    private String name;
    private List<Theaters>  theaters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Theaters> getTheaters() {
        return theaters;
    }

    public void setTheaters(List<Theaters> theaters) {
        this.theaters = theaters;
    }
}
