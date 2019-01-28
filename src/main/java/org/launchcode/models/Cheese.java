package org.launchcode.models;

public class Cheese {
    private String name;
    private String description;

    public Cheese(String name) {
        this.name = name;
    }

    public Cheese(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
