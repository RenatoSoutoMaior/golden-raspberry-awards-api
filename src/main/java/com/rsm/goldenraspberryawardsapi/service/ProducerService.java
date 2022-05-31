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

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProducerService {

    private final ProducerRepository producerRepository;
    private final MovieProducerRepository movieProducerRepository;
    private static final String REGEX = ", | and ";

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

        Map<Producer, List<MovieProducer>> movieProducersByProducers = movieProducers.stream().collect(Collectors.groupingBy(MovieProducer::getProducer));
        List<ProducerAwardsDTO> producerAwards = fillProducersAwardsAttributes(movieProducersByProducers);

        ProducerIntervalBetweenAwardsDTO producerIntervalBetweenAwardsDTO = new ProducerIntervalBetweenAwardsDTO();
        producerIntervalBetweenAwardsDTO.setMax(findInterval(producerAwards, IntervalType.MAX));
        producerIntervalBetweenAwardsDTO.setMin(findInterval(producerAwards, IntervalType.MIN));

        return producerIntervalBetweenAwardsDTO;
    }

    private List<ProducerAwardsDTO> findInterval(List<ProducerAwardsDTO> producerAwards, IntervalType intervalType) {
        if (intervalType.equals(IntervalType.MAX)) {
            return producerAwards.stream().filter(
                    producerInterval -> producerInterval.getInterval().equals(
                            producerAwards.stream()
                                    .max(Comparator.comparing(ProducerAwardsDTO::getInterval))
                                    .orElseThrow(NoSuchElementException::new).getInterval()
                    )
            ).collect(Collectors.toList());
        } else {
            return producerAwards.stream().filter(
                    producerInterval -> producerInterval.getInterval().equals(
                            producerAwards.stream()
                                    .min(Comparator.comparing(ProducerAwardsDTO::getInterval))
                                    .orElseThrow(NoSuchElementException::new).getInterval()
                    )
            ).collect(Collectors.toList());
        }
    }

    private List<ProducerAwardsDTO> fillProducersAwardsAttributes(Map<Producer, List<MovieProducer>> moviesByProducer) {
        List<ProducerAwardsDTO> producerAwards = new ArrayList<>();

        moviesByProducer.forEach((producer, movieProducer) -> {
            movieProducer.sort(Comparator.comparing(movieProducer1 -> movieProducer1.getMovie().getYear()));
            if (movieProducer.size() > 1) {
                movieProducer.forEach(movie -> {
                    MovieProducer nextMovie = getNextMovie(movieProducer, movie);
                    if (nextMovie != null) {
                        ProducerAwardsDTO producerAwardsDTO = new ProducerAwardsDTO(producer.getName(), movie.getMovie().getYear(), nextMovie.getMovie().getYear());
                        producerAwardsDTO.setInterval(producerAwardsDTO.getFollowingWin() - producerAwardsDTO.getPreviousWin());
                        producerAwards.add(producerAwardsDTO);
                    }
                });
            }
        });
        return producerAwards;
    }

    private MovieProducer getNextMovie(List<MovieProducer> winningMovies, MovieProducer currentMovie) {
        int index = winningMovies.indexOf(currentMovie);
        if (index < 0 || index + 1 == winningMovies.size()) {
            return null;
        }
        return winningMovies.get(index + 1);
    }
}
