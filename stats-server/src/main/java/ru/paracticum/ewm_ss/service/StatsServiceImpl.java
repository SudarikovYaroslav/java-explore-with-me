package ru.paracticum.ewm_ss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.paracticum.ewm_ss.dto.HitPostDto;
import ru.paracticum.ewm_ss.dto.HitResponseDto;
import ru.paracticum.ewm_ss.mapper.HitMapper;
import ru.paracticum.ewm_ss.model.Hit;
import ru.paracticum.ewm_ss.model.HitSearchParams;
import ru.paracticum.ewm_ss.repository.HitRepository;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final HitRepository hitRepo;

    @Override
    public void postHit(HitPostDto dto) {
        Hit hit = HitMapper.toModel(dto);
        hitRepo.save(hit);
    }

    @Override
    public List<HitResponseDto> getHits(HitSearchParams params) {
        Specification<Hit> specification = getSpecification(params);
        List<Hit> hits = hitRepo.findAll(specification);
        List<HitResponseDto> dtos = hits.stream().map((hit -> HitMapper.toDto(hit, hitRepo))).collect(Collectors.toList());
        return dtos;
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
            if (null != params.getUnique()){
                query.distinct(true);
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}