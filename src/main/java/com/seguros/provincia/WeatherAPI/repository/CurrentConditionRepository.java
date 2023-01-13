package com.seguros.provincia.WeatherAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seguros.provincia.WeatherAPI.domain.CurrentCondition;

@Repository
public interface CurrentConditionRepository extends JpaRepository<CurrentCondition, String> {

}
