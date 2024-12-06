package com.edu.cibertec.CL2_RIOS_SOREN_MVC.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer filmId;
    private String title;
    private String description;
    private Integer releaseYear;
    private Integer originalLanguageId;
    private Integer rentalDuration;
    private Double rentalRate;
    private Integer length;
    private Double replacementCost;
    private String rating;
    private String specialFeatures;
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Inventory> inventories;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Actor> actors;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Category> categories;

}
