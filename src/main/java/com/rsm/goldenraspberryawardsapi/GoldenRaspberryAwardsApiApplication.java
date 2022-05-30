package com.rsm.goldenraspberryawardsapi;

import com.rsm.goldenraspberryawardsapi.service.CSVReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GoldenRaspberryAwardsApiApplication {

    private final CSVReaderService csvReaderService;

    @Autowired
    public GoldenRaspberryAwardsApiApplication(CSVReaderService csvReaderService) {
        this.csvReaderService = csvReaderService;
    }

    public static void main(String[] args) {
        SpringApplication.run(GoldenRaspberryAwardsApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> csvReaderService.getMoviesFromCSV();
    }

}
