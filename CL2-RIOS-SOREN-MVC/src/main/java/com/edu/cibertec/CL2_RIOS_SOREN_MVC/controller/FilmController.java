package com.edu.cibertec.CL2_RIOS_SOREN_MVC.controller;

import com.edu.cibertec.CL2_RIOS_SOREN_MVC.dto.FilmDetailDto;
import com.edu.cibertec.CL2_RIOS_SOREN_MVC.dto.FilmDto;
import com.edu.cibertec.CL2_RIOS_SOREN_MVC.entity.Language;
import com.edu.cibertec.CL2_RIOS_SOREN_MVC.service.FilmService;
import com.edu.cibertec.CL2_RIOS_SOREN_MVC.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    // Redirigir la raíz "/" a "/films/list"
    @GetMapping("/")
    public String redirectToFilms() {
        return "redirect:/films/list";
    }

    @GetMapping("/list")
    private String listFilms(Model model) {
        List<FilmDto> films = filmService.findAllFilms();
        model.addAttribute("films", films);
        return "films-list";
    }

    @GetMapping("/detail/{id}")
    public String filmDetail(@PathVariable Integer id, Model model) {
        FilmDetailDto film = filmService.findFilmById(id);
        model.addAttribute("film", film);
        return "film-detail";
    }

    @GetMapping("/edit/{id}")
    public String editFilmForm(@PathVariable Integer id, Model model) {
        FilmDetailDto film = filmService.findFilmById(id);
        model.addAttribute("film", film);
        return "film-edit";
    }

    @PostMapping("/edit")
    public String editFilm(@ModelAttribute FilmDetailDto filmDetailDto) {
        filmService.updateFilm(filmDetailDto);
        return "redirect:/films/list";
    }

    @Autowired
    private LanguageService languageService;

    @GetMapping("/new")
    public String newFilmForm(Model model) {
        List<Language> languages = languageService.findAllLanguages();
        FilmDetailDto newFilm = new FilmDetailDto(
                null, "", "", null, null, null, null, null, null, null, null, null, null
        );
        model.addAttribute("film", newFilm);
        model.addAttribute("languages", languages);
        return "film-new";
    }

    @PostMapping("/new")
    public String saveFilm(@ModelAttribute FilmDetailDto filmDetailDto) {
        // Validar que se ha seleccionado un idioma
        if (filmDetailDto.languageId() == null) {
            throw new IllegalArgumentException("Language must be selected");
        }

        filmService.saveFilm(filmDetailDto);
        return "redirect:/films/list";
    }

    @PostMapping("/delete/{id}")
    public String deleteFilm(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        filmService.deleteFilm(id);
        redirectAttributes.addFlashAttribute("message", "Film deleted successfully.");
        return "redirect:/films/list";
    }

}
