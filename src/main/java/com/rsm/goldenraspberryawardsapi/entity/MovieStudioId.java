package com.rsm.goldenraspberryawardsapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@Getter
@Setter
public class MovieStudioId implements Serializable {

    private Long movieID;
    private Long studioID;

    public MovieStudioId(Long movieID, Long studioID) {
        this.movieID = movieID;
        this.studioID = studioID;
    }
}
