package com.edu.cibertec.CL2_RIOS_SOREN_MVC.service.impl;

import com.edu.cibertec.CL2_RIOS_SOREN_MVC.entity.Language;
import com.edu.cibertec.CL2_RIOS_SOREN_MVC.repository.LanguageRepository;
import com.edu.cibertec.CL2_RIOS_SOREN_MVC.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service // Esto registra la clase como un bean en el contenedor de Spring
public class LanguageServiceImpl implements LanguageService {

    @Autowired
    private LanguageRepository languageRepository; // Inyectar el repositorio

    @Override
    public List<Language> findAllLanguages() {
        List<Language> languages = new ArrayList<>();
        languageRepository.findAll().forEach(languages::add); // Convertir Iterable a List
        return languages;
    }

    @Override
    public Language findLanguageByName(String name) {
        return languageRepository.findByName(name);
    }
}


