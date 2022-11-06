package ru.paracticum.ss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import ru.paracticum.ss.dto.UtilDto;
import ru.paracticum.ss.model.Hit;

import java.util.List;

public interface HitRepository extends JpaRepository<Hit, Long>, JpaSpecificationExecutor<Hit> {
    @Query("select count (hits) from hits where uri like ?1")
    long getCountHitsByUri(String uri);

    @Query("select new ru.paracticum.ss.dto.UtilDto(h.hitId , count (h)) " +
            "from hits as h where h.uri in ?1 group by h.hitId")
    List<UtilDto> getContHitsByUris(List<String> uris);

    @Query("select  count (hits) from hits where eventId = ?1")
    long getCountHitsByEventId(Long id);

    @Query("select  new ru.paracticum.ss.dto.UtilDto(h.eventId, count (h)) " +
            "from hits as h where h.eventId in ?1 group by h.eventId")
    List<UtilDto> getCountHitsByEventIds(List<Long> eventIds);
}
