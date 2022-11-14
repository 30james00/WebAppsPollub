package com.mstolarz.pai_spring.controllers;

import com.mstolarz.pai_spring.beans.Pracownik;
import com.mstolarz.pai_spring.dao.PracownikDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class PracownikController {
    @Autowired
    PracownikDao dao; //wstrzyknięcie dao z pliku XML

    /* Wynikiem działania metody jest przekazanie danych w modelu do
     * strony widoku addForm.jsp, która wyświetla formularz
     * wprowadzania danych, a „command” jest zastrzeżonym atrybutem
     * żądania, umożliwiającym wyświetlenie danych obiektu pracownika
     * w formularzu.
     */
    @RequestMapping("/addForm")
    public String showform(Model m) {
        m.addAttribute("command", new Pracownik());
        return "addForm"; //przekiekrowanie do addForm.jsp
    }

    /* Metoda obsługuje zapis pracownika do BD. @ModelAttribute
     * umozliwia pobranie danych z żądania do obiektu pracownika.
     * Jawnie wskazano RequestMethod.POST (domyślnie jest to GET)
     */
    @RequestMapping(value = "/save", method =
            RequestMethod.POST)
    public String save(@ModelAttribute("pr") Pracownik pr) {
        dao.save(pr);
        return "redirect:/viewAll";
        //przekierowanie do endpointa o URL: /viewAll
    }

    /* Metoda pobiera listę pracowników z BD i umieszcza je w modelu */
    @RequestMapping("/viewAll")
    public String viewAll(Model m) {
        List<Pracownik> list = dao.getAll();
        m.addAttribute("list", list);
        return "viewAll"; //przejście do widoku viewAll.jsp
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model m) {
        Pracownik pracownik = dao.getPracownikById(id);
        if(pracownik == null) return "redirect:/viewAll";
        m.addAttribute("command", pracownik);
        return "editForm";
    }

    @RequestMapping(value = "/edit/editSave", method = RequestMethod.POST)
    public String editSave(@ModelAttribute("pr") Pracownik pr) {
        dao.update(pr);
        return "redirect:/viewAll";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        dao.delete(id);
        return "redirect:/viewAll";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(HttpServletRequest req,
                                    Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("errorPage");
        return mav;
    }
}