package com.waracle.cakemgr.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import com.waracle.cakemgr.dao.CakeEntity;
import com.waracle.cakemgr.repository.CakeRepository;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CakeServiceImplTest {

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private CakeRepository cakeRepository;

  @InjectMocks
  private CakeServiceImpl cakeService;

  @Test
  @DisplayName("Should return cakes in memory DB")
  void getAllCakesInMemory() {
    //Given
    CakeEntity cake = new CakeEntity("title","description","image");
    CakeEntity cake2 = new CakeEntity("title2","description2","image2");
    given(cakeRepository.findAll()).willReturn(Arrays.asList(cake, cake2));

    //When
    final List<CakeEntity> cakes = cakeService.getCakes();

    //Then
    assertThat(cakes.get(0), is(cake));
    assertThat(cakes.size(), is(2));
  }

  @Test
  @DisplayName("Should return expected object from third party client")
  void downloadCakes() {
    //Given
    String jsonData = "test data";
    final String downloadURL = "https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json";
    Mockito.when(restTemplate.getForEntity(downloadURL, String.class))
        .thenReturn(new ResponseEntity<>(jsonData, HttpStatus.OK));

    //When
    String results = cakeService.downloadCakes();
    assertThat(results, is(jsonData));
  }

  @Test
  @DisplayName("Should add and return new cake")
  void addCake() {
    //Given
    CakeEntity cake = new CakeEntity("test title", "test description", "test image");
    given(cakeRepository.save(cake)).willReturn(cake);

    //When
    CakeEntity result = cakeService.addCake(cake);

    //Then
    assertThat(result, is(cake));
  }

  @Test
  @DisplayName("Should add and return new cakes")
  void addCakes() {
    //Given
    CakeEntity cake = new CakeEntity("test title", "test description", "test image");
    CakeEntity cake2 = new CakeEntity("test title2", "test description2", "test image2");
    given(cakeRepository.saveAll(Arrays.asList(cake,cake2))).willReturn(Arrays.asList(cake,cake2));

    //When
    List<CakeEntity> result = cakeService.addCakes(Arrays.asList(cake,cake2));

    //Then
    assertThat(result.size(), is(2));
    assertThat(result.get(0), is(cake));
  }
}