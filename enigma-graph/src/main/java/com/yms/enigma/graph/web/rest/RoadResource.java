package com.yms.enigma.graph.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yms.enigma.graph.domain.Road;
import com.yms.enigma.graph.service.PointService;
import com.yms.enigma.graph.service.RoadService;
import com.yms.enigma.graph.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Razvan.Simion on 4/17/2016.
 */
@RestController
@RequestMapping("/api/roads")
public class RoadResource {

    @Inject
    PointService pointService;

    @Inject
    RoadService roadService;


    private final Logger log = LoggerFactory.getLogger(RoadResource.class);

    @RequestMapping(
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Road> save(@Valid @RequestBody Road road) throws URISyntaxException {
        Road result = roadService.save(road);
        return ResponseEntity.created(new URI("/api/roads/" + result.getRoadId()))
            .headers(HeaderUtil.createEntityCreationAlert("road",null))
            .body(result);
    }
}
