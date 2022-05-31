package com.rsm.goldenraspberryawardsapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ProducerIntervalBetweenAwardsDTO {

    private List<ProducerAwardsDTO> min = new ArrayList<>();
    private List<ProducerAwardsDTO> max = new ArrayList<>();

    public void setMin(List<ProducerAwardsDTO> min) {
        this.min = min;
    }

    public void setMax(List<ProducerAwardsDTO> max) {
        this.max = max;
    }
}
