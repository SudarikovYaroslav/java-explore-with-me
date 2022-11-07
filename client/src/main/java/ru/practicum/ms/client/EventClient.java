package ru.practicum.ms.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.ms.client.dto.BoxDto;
import ru.practicum.ms.client.dto.HitDto;
import ru.practicum.ms.client.dto.UtilDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EventClient {

    public static final String ID_NAME = "id";
    public static final String PARAM = "?ids=";
    public static final String DELIMITER = ",";
    public static final String API_HIT_PREFIX = "/hit";
    public static final String APP_NAME = "ewm-service";
    public static final String API_STATS_PREFIX = "/stats";
    public static final String API_VIEWS_PREFIX = "/views";
    public static final String BASE_PATH = "http://localhost:9090"; // "http://stats-server:9090"

    private final RestTemplate hitRest;
    private final RestTemplate statsRest;
    private final RestTemplate viewsRest;

    @Autowired
    public EventClient(@Value("http://localhost/8080") String serverUrl, RestTemplateBuilder builder) {
        hitRest = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_HIT_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build();

        statsRest = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_STATS_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build();

        viewsRest = builder
                .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_VIEWS_PREFIX))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new)
                .build();
    }

    public void postHit(String endpoint, String clientIp, Long eventId) {
        HitDto dto = new HitDto(APP_NAME, endpoint, clientIp, DateTimeMapper.toString(LocalDateTime.now()), eventId);
        HttpEntity<HitDto> requestEntity = new HttpEntity<>(dto);
        String path = BASE_PATH + API_HIT_PREFIX;
        hitRest.exchange(path, HttpMethod.POST, requestEntity, HitDto.class);
    }

    public ResponseEntity<Long> getViewsByEventId(Long eventId) {
        String path = BASE_PATH + API_VIEWS_PREFIX + "/" + eventId;
        return viewsRest.exchange(path, HttpMethod.GET, null, Long.class);
    }

    public List<UtilDto> getViewsByEventIds(List<Long> eventIds) {
        StringBuilder pathBuilder = new StringBuilder();
        pathBuilder.append(BASE_PATH).append(API_VIEWS_PREFIX).append(PARAM);
        Map<String, Object> params = new HashMap<>();

        for (int i = 0; i < eventIds.size(); i++) {
            pathBuilder.append(eventIds.get(i)).append(DELIMITER);
            String key = ID_NAME + i;
            params.put(key, eventIds.get(i));
        }
        pathBuilder.deleteCharAt(pathBuilder.length() - 1);
        String path = pathBuilder.toString();
        BoxDto response = viewsRest.exchange(path, HttpMethod.GET, null, BoxDto.class, params).getBody();
        assert response != null;
        return response.getUtilDtos();
    }
}
