package com.rsm.goldenraspberryawardsapi.controllers;

import com.rsm.goldenraspberryawardsapi.dto.ProducerIntervalBetweenAwardsDTO;
import com.rsm.goldenraspberryawardsapi.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
public class ProducerController {

    private final ProducerService producerService;

    @Autowired
    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @GetMapping("/interval-between-awards")
    public ResponseEntity<ProducerIntervalBetweenAwardsDTO> getIntervalBetweenAwards() {
        ProducerIntervalBetweenAwardsDTO producerIntervalBetweenAwardsDTO = producerService.getIntervalBetweenAwards();

        if (producerIntervalBetweenAwardsDTO.getMax().isEmpty() && producerIntervalBetweenAwardsDTO.getMin().isEmpty()) {
            return new ResponseEntity<>(producerIntervalBetweenAwardsDTO, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(producerIntervalBetweenAwardsDTO, HttpStatus.OK);
    }
}
