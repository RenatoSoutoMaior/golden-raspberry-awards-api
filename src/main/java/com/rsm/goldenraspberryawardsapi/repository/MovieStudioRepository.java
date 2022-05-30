package com.rsm.goldenraspberryawardsapi.repository;

import com.rsm.goldenraspberryawardsapi.entity.MovieStudio;
import com.rsm.goldenraspberryawardsapi.entity.MovieStudioId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieStudioRepository extends JpaRepository<MovieStudio, MovieStudioId> {

}
