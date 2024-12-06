package com.edu.cibertec.CL2_RIOS_SOREN_MVC.service;

import com.edu.cibertec.CL2_RIOS_SOREN_MVC.dto.FilmDetailDto;
import com.edu.cibertec.CL2_RIOS_SOREN_MVC.dto.FilmDto;
import com.edu.cibertec.CL2_RIOS_SOREN_MVC.entity.Film;

import java.util.List;

public interface FilmService {
    List<FilmDto> findAllFilms();
    FilmDetailDto findFilmById(int id);
    Film saveFilm(FilmDetailDto filmDetailDto);

    Boolean updateFilm(FilmDetailDto filmDetailDto);
    void deleteFilm(int id);
}
