package com.rsm.goldenraspberryawardsapi.repository;

import com.rsm.goldenraspberryawardsapi.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Producer findByName(String name);

}
