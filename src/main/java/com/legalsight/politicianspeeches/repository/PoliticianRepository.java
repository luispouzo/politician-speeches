package com.legalsight.politicianspeeches.repository;

import com.legalsight.politicianspeeches.model.Politician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoliticianRepository extends JpaRepository<Politician, Long> {
    @Query("""
          SELECT p
          FROM Politician p
          WHERE (:name IS null OR p.name = :name) AND (:email IS null OR p.email = :email)
            """)
    List<Politician> searchAll(@Param("name") String name, @Param("email") String email);
}
