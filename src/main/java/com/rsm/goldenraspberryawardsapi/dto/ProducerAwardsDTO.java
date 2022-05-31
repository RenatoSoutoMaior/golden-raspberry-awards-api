package com.rsm.goldenraspberryawardsapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProducerAwardsDTO {

    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

    public ProducerAwardsDTO(String producer, Integer previousWin, Integer followingWin) {
        this.producer = producer;
        this.previousWin = previousWin;
        this.followingWin = followingWin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProducerAwardsDTO that = (ProducerAwardsDTO) o;

        if (!producer.equals(that.producer)) return false;
        return interval.equals(that.interval);
    }

    @Override
    public int hashCode() {
        int result = producer.hashCode();
        result = 31 * result + interval.hashCode();
        return result;
    }
}
