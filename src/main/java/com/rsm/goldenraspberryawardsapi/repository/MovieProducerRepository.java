package com.rsm.goldenraspberryawardsapi.repository;

import com.rsm.goldenraspberryawardsapi.entity.MovieProducer;
import com.rsm.goldenraspberryawardsapi.entity.MovieProducerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieProducerRepository extends JpaRepository<MovieProducer, MovieProducerId> {

    @Query(value = "SELECT mp FROM MovieProducer AS mp JOIN mp.producer AS p JOIN mp.movie AS m WHERE m.winner = 'yes' ORDER BY m.year")
    List<MovieProducer> findByMovieWinnerOrderByYear(String winner);

}
