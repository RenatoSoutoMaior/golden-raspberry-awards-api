package com.rsm.goldenraspberryawardsapi.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "STUDIO")
@NoArgsConstructor
@Getter
@Setter
public class Studio {

    @Id
    @Column(name = "STUDIO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany
    private List<MovieStudio> movies = new ArrayList<>();

    public Studio(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
