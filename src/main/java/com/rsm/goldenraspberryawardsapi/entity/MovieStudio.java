package com.rsm.goldenraspberryawardsapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "MOVIE_STUDIO")
@NoArgsConstructor
@Getter
@Setter
public class MovieStudio {

    @EmbeddedId
    private MovieStudioId id;

    @ManyToOne
    @MapsId("movieID")
    private Movie movie;

    @ManyToOne
    @MapsId("studioID")
    private Studio studio;

    public MovieStudio(Movie movie, Studio studio) {
        this.id = new MovieStudioId(movie.getId(), studio.getId());
        this.movie = movie;
        this.studio = studio;
    }
}
