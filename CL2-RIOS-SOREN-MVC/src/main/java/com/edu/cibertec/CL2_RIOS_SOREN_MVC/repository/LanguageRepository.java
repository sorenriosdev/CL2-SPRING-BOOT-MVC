package com.edu.cibertec.CL2_RIOS_SOREN_MVC.repository;

import com.edu.cibertec.CL2_RIOS_SOREN_MVC.entity.Language;
import org.springframework.data.repository.CrudRepository;

public interface LanguageRepository extends CrudRepository<Language, Long> {
    Language findByName(String name);
}

