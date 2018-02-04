package com.itunes.api.service;

import com.itunes.api.model.SearchRequest;
import com.itunes.api.model.response.ItunesResponse;

public interface SearchService {

    ItunesResponse getResponse(SearchRequest request);
}
