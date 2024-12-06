package com.edu.cibertec.CL2_RIOS_SOREN_MVC.service;

import com.edu.cibertec.CL2_RIOS_SOREN_MVC.entity.Language;

import java.util.List;

public interface LanguageService {
    List<Language> findAllLanguages();
    Language findLanguageByName(String name);
}
