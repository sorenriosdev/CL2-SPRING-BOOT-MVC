package com.edu.cibertec.CL2_RIOS_SOREN_MVC.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actorId;
    private Date lastUpdate;

    @ManyToMany(mappedBy = "actors")
    private List<Film> films;

}
