package ru.paracticum.ewm_ss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.paracticum.ewm_ss.model.Hit;

public interface HitRepository extends JpaRepository<Hit, Long>, JpaSpecificationExecutor<Hit> {
    @Query("select count (hits) from hits where uri like ?1")
    long getCountHits(String uri);

    @Query("select  count (hits) from hits where eventId = ?1")
    long getCountHitsByEventId(Long id);
}
