package com.yms.enigma.graph.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.yms.enigma.graph.domain.Point;
import com.yms.enigma.graph.service.PointService;
import com.yms.enigma.graph.web.rest.dto.PointDTO;
import com.yms.enigma.graph.web.rest.mapper.PointMapper;
import com.yms.enigma.graph.web.rest.mapper.PointMapperImpl;
import com.yms.enigma.graph.web.rest.util.HeaderUtil;
import com.yms.enigma.graph.web.rest.util.PaginationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by Razvan.Simion on 4/17/2016.
 */
@RestController
@RequestMapping("/api/points")
public class PointResource {

    @Inject
    PointService pointService;

    private final Logger log = LoggerFactory.getLogger(PointResource.class);

    @RequestMapping(
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Point> save(@Valid @RequestBody Point point) throws URISyntaxException {
        Point result = pointService.save(point);
        return ResponseEntity.created(new URI("/api/points/" + result.getNodeId()))
            .headers(HeaderUtil.createEntityCreationAlert("point", result.getNodeId().toString()))
            .body(result);
    }

    @RequestMapping(
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<List<PointDTO>> findAll(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Answers");
        Page<Point> page = pointService.findAll(pageable);

        PointMapperImpl pointMapper =  new PointMapperImpl();
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/points");
        return new ResponseEntity<>(pointMapper.pointsToPointsDTOs(page.getContent()), headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{nodeId}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    @Transactional(readOnly = true)
    public ResponseEntity<Point> get(@PathVariable Long nodeId)
        throws URISyntaxException {
        log.debug("REST request to get a page of Answers");
        Point point = pointService.findOne(nodeId);
        return new ResponseEntity<>(point, HttpStatus.OK);
    }
}
