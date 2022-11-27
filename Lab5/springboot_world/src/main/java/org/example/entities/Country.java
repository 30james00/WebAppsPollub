package org.example.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country {
    @Id
    private String code;
    @Column
    private String name;
    @Column
    private String continent;
    @Column
    private int population;
    @Column
    private double surfaceArea;

    @Override
    public String toString() {
        return "Country{" +
                "Code='" + code + '\'' +
                ", Name='" + name + '\'' +
                ", Continent='" + continent + '\'' +
                ", Population=" + population +
                ", SurfaceArea=" + surfaceArea +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(double surfaceArea) {
        this.surfaceArea = surfaceArea;
    }
}
