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
public class MovieProducerId implements Serializable {

    private Long movieID;
    private Long producerID;

    public MovieProducerId(Long movieID, Long producerID) {
        this.movieID = movieID;
        this.producerID = producerID;
    }
}
