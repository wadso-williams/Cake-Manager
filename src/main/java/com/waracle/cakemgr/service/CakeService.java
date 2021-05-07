package com.waracle.cakemgr.service;

import com.waracle.cakemgr.dao.CakeEntity;

import java.util.List;

/**
 * Service for managing {@link CakeEntity} details
 */
public interface CakeService {

  /**
   * Retrieves a list of all cakes
   *
   * @return the lists of cakes
   */
  List<CakeEntity> getCakes();

  /**
   * Adds new cakes to the existing cakes
   *
   * @param cakes a list of new cakes dto
   * @return the newly added cake
   */
  List<CakeEntity> addCakes(List<CakeEntity> cakes);

  /**
   * Downloads existing cakes from the current existing system
   *
   * @return a json String of existing cakes
   */
  String downloadCakes();

  /**
   * Adds a new cake to the current cakes
   *
   * @param cake a cake dto
   * @return the newly added cake
   */
  CakeEntity addCake(CakeEntity cake);
}
