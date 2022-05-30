package com.rsm.goldenraspberryawardsapi.service;

import com.rsm.goldenraspberryawardsapi.entity.Movie;
import com.rsm.goldenraspberryawardsapi.entity.MovieStudio;
import com.rsm.goldenraspberryawardsapi.entity.Studio;
import com.rsm.goldenraspberryawardsapi.repository.MovieStudioRepository;
import com.rsm.goldenraspberryawardsapi.repository.StudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudioService {

    private final StudioRepository studioRepository;
    private final MovieStudioRepository movieStudioRepository;
    private static final String REGEX = ",| and ";

    @Autowired
    public StudioService(StudioRepository studioRepository, MovieStudioRepository movieStudioRepository) {
        this.studioRepository = studioRepository;
        this.movieStudioRepository = movieStudioRepository;
    }

    void saveStudios(Movie movie, String studios) {
        String[] splitedStudios = studios.split(REGEX);

        for (String currentStudio : splitedStudios) {
            Studio studio = studioRepository.findByName(currentStudio.trim());

            if (studio == null) {
                studio = new Studio(currentStudio.trim());
                studioRepository.save(studio);
            }

            movieStudioRepository.save(new MovieStudio(movie, studio));
        }
    }
}
