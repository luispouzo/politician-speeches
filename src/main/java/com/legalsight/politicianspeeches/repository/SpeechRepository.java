package com.legalsight.politicianspeeches.repository;

import com.legalsight.politicianspeeches.model.Speech;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Repository
public interface SpeechRepository extends JpaRepository<Speech, Long>, JpaSpecificationExecutor<Speech> {
    List<Speech> findAllBySpeechDateBetween(Date startTime, Date endTime);
    List<Speech> findAllByAuthorId(Long authorId);
    List<Speech> findAllBySubject(String subject);
    boolean existsByIdAndAuthorId(@NotNull Long id, @NotNull Long authorId);

    @Query("""
      SELECT DISTINCT s
      FROM Speech s
      INNER JOIN s.keywords k 
      WHERE (:text IS null OR fts(:text) = true)
        AND (:subject IS null OR s.subject = :subject)
        AND ((cast(:startDate as date) IS null AND cast(:endDate as date) is null) OR s.speechDate BETWEEN :startDate AND :endDate)
        AND (:keywordName is null OR k.name = :keywordName)
        AND (:authorName IS null OR s.author.name = :authorName)
        """)
    List<Speech> searchAll(@Param("subject") String subject,
                           @Param("authorName") String authorName,
                           @Param("text") String text,
                           @Param("keywordName") String keywordName,
                           @Param("startDate") Date startDate,
                           @Param("endDate") Date endDate);
}
