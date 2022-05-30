package com.rsm.goldenraspberryawardsapi.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PRODUCER")
@NoArgsConstructor
@Getter
@Setter
public class Producer {

    @Id
    @Column(name = "PRODUCER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @OneToMany
    private List<MovieProducer> movies = new ArrayList<>();

    public Producer(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
}
