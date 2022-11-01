package ru.paracticum.ewm_ss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.paracticum.ewm_ss.dto.HitPostDto;
import ru.paracticum.ewm_ss.dto.HitResponseDto;
import ru.paracticum.ewm_ss.mapper.HitMapper;
import ru.paracticum.ewm_ss.model.App;
import ru.paracticum.ewm_ss.model.Hit;
import ru.paracticum.ewm_ss.model.HitSearchParams;
import ru.paracticum.ewm_ss.repository.AppRepository;
import ru.paracticum.ewm_ss.repository.HitRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final HitRepository hitRepo;
    private final AppRepository appRepo;

    @Override
    @Transactional
    public void postHit(HitPostDto dto) {
        App app = appRepo.findByName(dto.getApp()).orElse(null);
        if (app == null) {
            app = appRepo.save(new App(dto.getApp()));
        }
        Hit hit = HitMapper.toModel(dto, app);
        hitRepo.save(hit);
    }

    @Override
    public List<HitResponseDto> getHits(HitSearchParams params) {
        Specification<Hit> specification = getSpecification(params);
        List<Hit> hits = hitRepo.findAll(specification);
        return hits.stream().map((hit -> HitMapper.toDto(hit, hitRepo))).collect(Collectors.toList());
    }

    private Specification<Hit> getSpecification(HitSearchParams params) {
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (null != params.getStart()) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("timeStamp"), params.getStart()));
            }
            if (null != params.getEnd()) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("timeStamp"), params.getEnd()));
            }
            if (null != params.getUris() && !params.getUris().isEmpty()) {
                for (String uri : params.getUris()) {
                    predicates.add(criteriaBuilder.equal(root.get("uri"), uri));
                }
            }
            if (null != params.getUnique()) {
                query.distinct(true);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}