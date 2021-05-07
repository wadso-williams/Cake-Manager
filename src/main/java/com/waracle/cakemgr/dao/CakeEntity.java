package com.waracle.cakemgr.dao;

import org.hibernate.annotations.DynamicUpdate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.Objects;

@Entity
@DynamicUpdate
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "Cake", uniqueConstraints = {@UniqueConstraint(columnNames = "ID"), @UniqueConstraint(columnNames = "TITLE")})
public class CakeEntity implements Serializable {

  private static final long serialVersionUID = -1798070786993154676L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", unique = true, nullable = false)
  private Long cakeId;

  @Column(name = "TITLE", unique = true, nullable = false, length = 100)
  private String title;

  @Column(name = "DESC", nullable = false, length = 100)
  private String desc;

  @Column(name = "IMAGE", nullable = false, length = 300)
  private String image;

  public CakeEntity() {
  }

  public CakeEntity(String title, String desc, String image) {
    this.title = title;
    this.desc = desc;
    this.image = image;
  }

  public Long getCakeId() {
    return cakeId;
  }

  public void setCakeId(Long cakeId) {
    this.cakeId = cakeId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CakeEntity that = (CakeEntity) o;
    return Objects.equals(cakeId, that.cakeId) && title.equals(that.title) && desc.equals(that.desc) && image.equals(that.image);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cakeId, title, desc, image);
  }
}