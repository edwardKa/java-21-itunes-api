package com.itunes.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itunes.api.model.SearchRequest;
import com.itunes.api.model.response.ItunesResponse;
import com.itunes.api.service.SearchService;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SearchServiceImpl implements SearchService {

    static Map<SearchRequest, ItunesResponse> cachedMap = new HashMap<>();

    @Override
    @SneakyThrows
    public ItunesResponse getResponse(SearchRequest request) {

        ItunesResponse response = cachedMap.getOrDefault(request, null);

        return response == null ? getServerResponse(request) : response;
    }

    @SneakyThrows
    private ItunesResponse getServerResponse(SearchRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://itunes.apple.com/search";

        StringBuilder builder = new StringBuilder(url);
        builder.append("?")
                .append("term=").append(request.getTerm());

        if (request.getLimit() != null) {
            builder.append("&limit=").append(request.getLimit());
        }

        if (request.getEntityType() != null) {
            builder
                    .append("&entity=")
                    .append(request.getEntityType().getName());
        }

        ResponseEntity<String> entity =
                restTemplate.getForEntity(builder.toString(), String.class);

        String json = entity.getBody();

        ItunesResponse response = new ObjectMapper().readValue(json, ItunesResponse.class);

        cachedMap.put(request, response);
        return response;
    }
}
