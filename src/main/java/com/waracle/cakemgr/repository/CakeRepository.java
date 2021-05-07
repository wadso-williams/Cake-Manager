package com.waracle.cakemgr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.waracle.cakemgr.dao.CakeEntity;

public interface CakeRepository extends JpaRepository<CakeEntity, Long> {
}
