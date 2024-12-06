package com.edu.cibertec.CL2_RIOS_SOREN_MVC.service.impl;

import com.edu.cibertec.CL2_RIOS_SOREN_MVC.dto.FilmDetailDto;
import com.edu.cibertec.CL2_RIOS_SOREN_MVC.dto.FilmDto;
import com.edu.cibertec.CL2_RIOS_SOREN_MVC.entity.Film;
import com.edu.cibertec.CL2_RIOS_SOREN_MVC.entity.Language;
import com.edu.cibertec.CL2_RIOS_SOREN_MVC.repository.FilmRepository;
import com.edu.cibertec.CL2_RIOS_SOREN_MVC.service.FilmService;
import jakarta.persistence.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    private FilmRepository filmRepository;

    @Override
    public List<FilmDto> findAllFilms() {
        List<FilmDto> films = new ArrayList<>();
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach(film -> {
            FilmDto filmDto = new FilmDto(film.getFilmId(),
                    film.getTitle(),
                    film.getRentalDuration(),
                    film.getRentalRate());
            films.add(filmDto);
        });
        return films;
    }

    @Override
    public FilmDetailDto findFilmById(int id) {
        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(film -> new FilmDetailDto(film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguage().getLanguageId(),
                film.getLanguage().getName(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating(),
                film.getSpecialFeatures(),
                film.getLastUpdate())
        ).orElse(null);


    }

    @CacheEvict(value = "films", allEntries = true)
    @Override
    public Film saveFilm(FilmDetailDto filmDetailDto) {
        Film film = new Film();
        film.setTitle(filmDetailDto.title());
        film.setDescription(filmDetailDto.description());
        film.setReleaseYear(filmDetailDto.releaseYear());
        film.setRentalDuration(filmDetailDto.rentalDuration());
        film.setRentalRate(filmDetailDto.rentalRate());
        film.setLength(filmDetailDto.length());
        film.setReplacementCost(filmDetailDto.replacementCost());
        film.setRating(filmDetailDto.rating());
        film.setSpecialFeatures(filmDetailDto.specialFeatures());

        // Agregar esta línea para establecer la fecha de última actualización
        film.setLastUpdate(new Date());

        // Asignar lenguaje
        Language language = new Language();
        language.setLanguageId(filmDetailDto.languageId());
        film.setLanguage(language);

        return filmRepository.save(film);
    }

    @CacheEvict(value = "films", allEntries = true)
    @Override
    public Boolean updateFilm(FilmDetailDto filmDetailDto) {
        Optional<Film> existingFilmOptional = filmRepository.findById(filmDetailDto.filmId());

        if (existingFilmOptional.isPresent()) {
            Film existingFilm = existingFilmOptional.get();

            existingFilm.setTitle(filmDetailDto.title());
            existingFilm.setDescription(filmDetailDto.description());
            existingFilm.setReleaseYear(filmDetailDto.releaseYear());
            existingFilm.setRentalDuration(filmDetailDto.rentalDuration());
            existingFilm.setRentalRate(filmDetailDto.rentalRate());
            existingFilm.setLength(filmDetailDto.length());
            existingFilm.setReplacementCost(filmDetailDto.replacementCost());
            existingFilm.setRating(filmDetailDto.rating());
            existingFilm.setSpecialFeatures(filmDetailDto.specialFeatures());

            filmRepository.save(existingFilm);
            return true;
        }

        return false;
    }

    @CacheEvict(value = "films", allEntries = true)
    @Override
    public void deleteFilm(int id) {
        filmRepository.deleteById(id);
    }
}
