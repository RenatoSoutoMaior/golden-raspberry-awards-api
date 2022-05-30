package com.rsm.goldenraspberryawardsapi.repository;

import com.rsm.goldenraspberryawardsapi.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<Studio, Long> {

    Studio findByName(String name);
}
