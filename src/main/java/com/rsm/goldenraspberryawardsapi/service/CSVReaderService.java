package com.rsm.goldenraspberryawardsapi.service;

import com.rsm.goldenraspberryawardsapi.entity.Movie;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Service
public class CSVReaderService {

    private final MovieService movieService;
    private final StudioService studioService;
    private final ProducerService producerService;
    private static final String MOVIELIST_FILE_PATH = "files/movielist.csv";

    @Autowired
    public CSVReaderService(MovieService movieService, StudioService studioService, ProducerService producerService) {
        this.movieService = movieService;
        this.studioService = studioService;
        this.producerService = producerService;
    }

    public void getMoviesFromCSV() throws IOException {
        Iterable<CSVRecord> csvRecords = readFile();

        for (CSVRecord csvRecord : csvRecords) {
            Movie movie = new Movie();
            movie.setYear(Integer.valueOf(csvRecord.get(0)));
            movie.setTitle(csvRecord.get(1));
            movie.setWinner(csvRecord.get(4).isEmpty() ? "no" : csvRecord.get(4).toLowerCase());
            movieService.saveMovie(movie);
            studioService.saveStudios(movie, csvRecord.get(2));
            producerService.saveProducers(movie, csvRecord.get(3));
        }
    }

    private Iterable<CSVRecord> readFile() throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(MOVIELIST_FILE_PATH);

        return CSVFormat.DEFAULT
                .builder()
                .setHeader()
                .setDelimiter(';')
                .build()
                .parse(new InputStreamReader(inputStream));
    }
}
