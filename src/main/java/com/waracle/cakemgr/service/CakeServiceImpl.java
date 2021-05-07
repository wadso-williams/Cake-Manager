package com.waracle.cakemgr.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.waracle.cakemgr.dao.CakeEntity;
import com.waracle.cakemgr.repository.CakeRepository;

import java.util.List;

/**
 * Default implementation of {@link CakeService}
 */
@Service
public class CakeServiceImpl implements CakeService {

  final CakeRepository cakeRepository;
  final RestTemplate httpClient;

  public CakeServiceImpl(CakeRepository cakeRepository, RestTemplate httpClient) {
    this.cakeRepository = cakeRepository;
    this.httpClient = httpClient;
  }

  @Override
  public List<CakeEntity> getCakes() {
    return cakeRepository.findAll();
  }

  @Override
  public String downloadCakes() {
    final String downloadURL = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";
    ResponseEntity<String> responseEntity = httpClient.getForEntity(downloadURL, String.class);
    return responseEntity.getStatusCode() == HttpStatus.OK ? responseEntity.getBody() : "";
  }

  @Override
  public List<CakeEntity> addCakes(List<CakeEntity> cakeEntities) {
    return cakeRepository.saveAll(cakeEntities);
  }

  @Override
  public CakeEntity addCake(CakeEntity cake) {
    return cakeRepository.save(cake);
  }

}