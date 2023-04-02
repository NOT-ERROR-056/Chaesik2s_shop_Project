package com.noterror.app.api.vegetarian;

import com.noterror.app.api.entity.Vegetarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VegetarianRepository extends JpaRepository<Vegetarian, String> {
    @Query(nativeQuery = true,
    value = "select * from vegetarian where levels > " +
            "(select levels from vegetarian where vegetarian_type = :vegetarianType) " +
            "or vegetarian_type = :vegetarianType")
    List<Vegetarian> findVegetarianTypes(@Param("vegetarianType") String vegetarianType);
}
