package com.rsm.goldenraspberryawardsapi.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "MOVIE")
@NoArgsConstructor
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "_YEAR")
    private Integer year;

    @NotNull
    @Column(name = "TITLE")
    private String title;

    @OneToMany
    private Set<MovieStudio> studios = new HashSet<>();

    @OneToMany
    private Set<MovieProducer> producers = new HashSet<>();

    @NotNull
    @Column(name = "WINNER")
    private String winner;

    public Movie(Integer year, String title, String winner) {
        this.year = year;
        this.title = title;
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }
}
