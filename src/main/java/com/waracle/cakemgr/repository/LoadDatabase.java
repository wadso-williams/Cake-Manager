package com.waracle.cakemgr.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waracle.cakemgr.dao.CakeEntity;
import com.waracle.cakemgr.service.CakeService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Preloads the database with the downloaded existing data from the current system
 */
@Configuration
public class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(CakeService cakeService) {
    return args -> {
      final String downloadedCakes = cakeService.downloadCakes();
      ObjectMapper mapper = new ObjectMapper();
      TypeReference<List<CakeEntity>> typeReference = new TypeReference<List<CakeEntity>>(){};
      List<CakeEntity> cakes = mapper.readValue(downloadedCakes,typeReference);
      log.info("Preloading " + cakeService.addCakes(cakes.stream().distinct().collect(Collectors.toList())));
    };
  }
}
