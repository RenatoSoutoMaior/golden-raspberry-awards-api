package com.rsm.goldenraspberryawardsapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
public class ProducerIntervalBetweenAwardsDTO {

    private Set<ProducerAwardsDTO> min = new HashSet<>();
    private Set<ProducerAwardsDTO> max = new HashSet<>();

    public void setMin(Set<ProducerAwardsDTO> min) {
        for (ProducerAwardsDTO producerAwardsDTO : min) {
            this.getMin().add(producerAwardsDTO);
        }
    }

    public void setMax(Set<ProducerAwardsDTO> max) {
        for (ProducerAwardsDTO producerAwardsDTO : max) {
            this.getMax().add(producerAwardsDTO);
        }
    }
}
