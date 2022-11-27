package org.example.controllers;

import org.example.entities.Country;
import org.example.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CountryController {
    @Autowired
    CountryRepository countryRepository;

    @RequestMapping("/")
    @ResponseBody
    public String mainPage() {
        return "Hello Spring Boot from mainPage() method!";
    }

    @RequestMapping("/country/continent/{name}")
    @ResponseBody
    public String countryByContinent(@PathVariable("name") String name) {
        var countries = countryRepository.findCountriesByContinent(name);
        return printList(countries);
    }

    @RequestMapping("/country/population/{min}/{max}")
    @ResponseBody
    public String countryByPopulation(@PathVariable("min") int min, @PathVariable("max") int max) {
        var countries = countryRepository.findCountriesByPopulationBetween(min, max);
        return printList(countries);
    }

    @RequestMapping("/country/surfaceArea/{min}/{max}")
    @ResponseBody
    public String countryBySurfaceArea(@PathVariable("min") double min, @PathVariable("max") double max) {
        var countries = countryRepository.findCountriesBySurfaceAreaBetween(min, max);
        return printList(countries);
    }

    private String printList(List<Country> countries) {
        StringBuilder odp = new StringBuilder();
        for (Country country : countries) {
            odp.append(country).append("<br>");
        }
        return odp.toString();
    }
}
