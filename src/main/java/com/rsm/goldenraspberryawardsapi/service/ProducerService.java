package com.rsm.goldenraspberryawardsapi.service;

import com.rsm.goldenraspberryawardsapi.dto.ProducerAwardsDTO;
import com.rsm.goldenraspberryawardsapi.dto.ProducerIntervalBetweenAwardsDTO;
import com.rsm.goldenraspberryawardsapi.entity.IntervalType;
import com.rsm.goldenraspberryawardsapi.entity.Movie;
import com.rsm.goldenraspberryawardsapi.entity.MovieProducer;
import com.rsm.goldenraspberryawardsapi.entity.Producer;
import com.rsm.goldenraspberryawardsapi.repository.MovieProducerRepository;
import com.rsm.goldenraspberryawardsapi.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProducerService {

    private final ProducerRepository producerRepository;
    private final MovieProducerRepository movieProducerRepository;
    private static final String REGEX = ",| and ";

    @Autowired
    public ProducerService(ProducerRepository producerRepository, MovieProducerRepository movieProducerRepository) {
        this.producerRepository = producerRepository;
        this.movieProducerRepository = movieProducerRepository;
    }

    void saveProducers(Movie movie, String producers) {
        String[] splitedProducers = producers.split(REGEX);

        for (String currentProducer : splitedProducers) {
            Producer producer = producerRepository.findByName(currentProducer.trim());

            if (producer == null) {
                producer = new Producer(currentProducer.trim());
                producerRepository.save(producer);
            }

            movieProducerRepository.save(new MovieProducer(movie, producer));
        }
    }

    public ProducerIntervalBetweenAwardsDTO getIntervalBetweenAwards() {
        List<MovieProducer> movieProducers = movieProducerRepository.findByMovieWinnerOrderByYear("yes");

        ProducerIntervalBetweenAwardsDTO producerIntervalBetweenAwardsDTO = new ProducerIntervalBetweenAwardsDTO();
        producerIntervalBetweenAwardsDTO.setMax(findInterval(movieProducers, IntervalType.MAX));
        producerIntervalBetweenAwardsDTO.setMin(findInterval(movieProducers, IntervalType.MIN));

        return producerIntervalBetweenAwardsDTO;
    }

    private Set<ProducerAwardsDTO> findInterval(List<MovieProducer> movieProducers, IntervalType intervalType) {
        Set<ProducerAwardsDTO> producerAwardsDTOS = new HashSet<>();

        ProducerAwardsDTO producerAwards = new ProducerAwardsDTO();
        producerAwards.setInterval(IntervalType.MAX.equals(intervalType) ? Integer.MIN_VALUE : Integer.MAX_VALUE);

        movieProducers.forEach(movieProducer1 -> movieProducers.forEach(movieProducer2 -> {
            if (!movieProducer1.getId().equals(movieProducer2.getId())) {
                if (movieProducer1.getProducer().equals(movieProducer2.getProducer())) {
                    Integer interval = Math.abs(movieProducer1.getMovie().getYear() - movieProducer2.getMovie().getYear());

                    if (IntervalType.MAX.equals(intervalType) && interval >= producerAwards.getInterval()) {
                        if (interval.equals(producerAwards.getInterval())) {
                            producerAwardsDTOS.add(fillProducerAwardsAttributes(new ProducerAwardsDTO(), movieProducer1, movieProducer2, interval));
                        } else {
                            producerAwardsDTOS.add(fillProducerAwardsAttributes(producerAwards, movieProducer1, movieProducer2, interval));
                        }
                    } else if (IntervalType.MIN.equals(intervalType) && interval <= producerAwards.getInterval()) {
                        if (interval.equals(producerAwards.getInterval())) {
                            producerAwardsDTOS.add(fillProducerAwardsAttributes(new ProducerAwardsDTO(), movieProducer1, movieProducer2, interval));
                        } else {
                            producerAwardsDTOS.add(fillProducerAwardsAttributes(producerAwards, movieProducer1, movieProducer2, interval));
                        }
                    }
                }
            }
        }));

        return producerAwardsDTOS;
    }

    private ProducerAwardsDTO fillProducerAwardsAttributes(ProducerAwardsDTO producerAwardsDTO, MovieProducer movieProducer1, MovieProducer movieProducer2, Integer interval) {
        producerAwardsDTO.setProducer(movieProducer1.getProducer().getName());
        producerAwardsDTO.setInterval(interval);
        producerAwardsDTO.setPreviousWin(movieProducer1.getMovie().getYear());
        producerAwardsDTO.setFollowingWin(movieProducer2.getMovie().getYear());

        return producerAwardsDTO;
    }
}
