package com.yms.enigma.graph.service;

import com.yms.enigma.graph.domain.Point;
import com.yms.enigma.graph.repository.PointRepository;
import com.yms.enigma.graph.web.rest.dto.PointDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created by Razvan.Simion on 4/17/2016.
 */
@Service
@Transactional
public class PointService {

    @Inject
    PointRepository pointRepository;


    private final Logger log = LoggerFactory.getLogger(PointService.class);

    public Point save(Point point) {
        Point newPoint = new Point(point.getLat(), point.getLng(), point.getName(),point.getQueryId());

        Point saved = pointRepository.save(newPoint);
        return new Point(saved.getName(),saved.getNodeId(),saved.getQueryId());
    }

    @Transactional(readOnly = true)
    public Page<Point> findAll(Pageable pageable) {
        log.debug("Request to get all Answers");
        Page<Point> result = pointRepository.findAll(pageable);
        return result;
    }


    @Transactional(readOnly = true)
    public Point findOne(Long nodeId) {
        log.debug("Request to get all Answers");
        Point result = pointRepository.findOne(nodeId);
        return result;
    }
}
