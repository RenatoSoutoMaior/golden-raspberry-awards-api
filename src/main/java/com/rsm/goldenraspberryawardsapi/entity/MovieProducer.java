package com.rsm.goldenraspberryawardsapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "MOVIE_PRODUCER")
@NoArgsConstructor
@Getter
@Setter
public class MovieProducer {

    @EmbeddedId
    private MovieProducerId id;

    @ManyToOne
    @MapsId("movieID")
    private Movie movie;

    @ManyToOne
    @MapsId("producerID")
    private Producer producer;

    public MovieProducer(Movie movie, Producer producer) {
        this.id = new MovieProducerId(movie.getId(), producer.getId());
        this.movie = movie;
        this.producer = producer;
    }
}
