package com.itunes.api.controller;

import com.itunes.api.model.SearchRequest;
import com.itunes.api.model.response.ItunesResponse;
import com.itunes.api.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @PostMapping("/query")
    public ItunesResponse search(@RequestBody @Valid SearchRequest sr,
                                 BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException();
        }
        long startTime = new Date().getTime();
        ItunesResponse response = searchService.getResponse(sr);

        long endDate = new Date().getTime();

        System.out.println("Time elapsed: " + (endDate - startTime));
        return response;
    }
}
