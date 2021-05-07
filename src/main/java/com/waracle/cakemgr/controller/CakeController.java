package com.waracle.cakemgr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.waracle.cakemgr.dao.CakeEntity;
import com.waracle.cakemgr.service.CakeService;

import java.util.List;

@PreAuthorize("isAuthenticated()")
@RestController
public class CakeController {

  public final CakeService cakeService;

  @Autowired
  public CakeController(CakeService cakeService) {
    this.cakeService = cakeService;
  }

  @PreAuthorize("permitAll()")
  @GetMapping(value = {"/"}, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<CakeEntity> getAllCakes() {
    return cakeService.getCakes();
  }

  @PreAuthorize("permitAll()")
  @PostMapping(value = "/",  produces = MediaType.APPLICATION_JSON_VALUE)
  public CakeEntity newCake(@RequestBody CakeEntity cake) {
    return cakeService.addCake(cake);
  }

  @PreAuthorize("permitAll()")
  @GetMapping(value = "/cakes", produces = MediaType.APPLICATION_JSON_VALUE)
  public String downloadAllCakes() {
    return cakeService.downloadCakes();
  }

  @PreAuthorize("permitAll()")
  @PostMapping(value = "/cakes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public List<CakeEntity> newCakes(@RequestBody List<CakeEntity> cakes) {
    return cakeService.addCakes(cakes);
  }

}