package com.rsm.goldenraspberryawardsapi.repository;

import com.rsm.goldenraspberryawardsapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
