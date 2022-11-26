package org.example.controllers;

import org.example.entities.Zadanie;
import org.example.repositories.ZadanieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Random;

@Controller
public class PageController {

    @Autowired
    public ZadanieRepository rep;

    @RequestMapping("/")
    @ResponseBody
    public String mainPage() {
        return "Hello Spring Boot from mainPage() method!";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String pageTwo() {
        return "Hello Spring Boot from pageTwo() method!";
    }

    @RequestMapping("/listaZadan")
    @ResponseBody
    public String listaZadan() {
        Zadanie zadanie = new Zadanie();
        //korzystając z obiektu repozytorium zapisujemy zadanie do bazy
        rep.save(zadanie);
        Iterable<Zadanie> zadania = rep.findAll();
        return printZadanie(zadania);
    }

    @RequestMapping("/listaZadanBulk")
    @ResponseBody
    public String listaZadanBulk() {
        StringBuilder odp = new StringBuilder();
        Zadanie zadanie;
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            zadanie = new Zadanie();
            zadanie.setNazwa("Zadanie " + i);
            zadanie.setOpis("Opis czynności do wykonania w zadaniu " + i);
            zadanie.setKoszt((double) random.nextInt(1000));
            zadanie.setWykonane(random.nextBoolean());
            rep.save(zadanie);
        }
        //korzystając z obiektu repozytorium zapisujemy zadanie do bazy
        //korzystając z repozytorium pobieramy wszystkie zadania z bazy
        for (Zadanie i : rep.findAll()) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        try {
            rep.deleteById(id);
        } catch (DataAccessException e) {
            return "redirect:/";
        }
        return "redirect:/listaZadan";
    }

    @RequestMapping("/listDone/{done}")
    @ResponseBody
    public String listDone(@PathVariable("done") boolean done) {
        List<Zadanie> zadania = rep.findByWykonane(done);
        return printZadanie(zadania);
    }

    @RequestMapping("/listCost/{max}")
    @ResponseBody
    public String listCostLessThan(@PathVariable("max") double max) {
        List<Zadanie> zadania = rep.findByKosztLessThan(max);
        return printZadanie(zadania);
    }

    @RequestMapping("/listCost/{min}/{max}")
    @ResponseBody
    public String listCostBetween(@PathVariable("min") double min, @PathVariable("max") double max) {
        List<Zadanie> zadania = rep.findByKosztBetween(min, max);
        return printZadanie(zadania);
    }

    private String printZadanie(Iterable<Zadanie> zadania) {
        StringBuilder odp = new StringBuilder();
        //korzystając z repozytorium pobieramy wszystkie zadania z bazy
        for (Zadanie i : zadania) {
            odp.append(i).append("<br>");
        }
        return odp.toString();
    }
}