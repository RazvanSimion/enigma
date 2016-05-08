package com.yms.enigma.graph.service;

import com.yms.enigma.graph.domain.Point;
import com.yms.enigma.graph.domain.Road;
import com.yms.enigma.graph.repository.PointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashSet;

/**
 * Created by Razvan.Simion on 4/18/2016.
 */

@Service
@Transactional
public class RoadService {

    @Inject
    PointRepository pointRepository;


    private final Logger log = LoggerFactory.getLogger(RoadService.class);


    public Road save(Road road) {

        Point startPoint = pointRepository.findOne(road.getStartPoint().getNodeId());
        Point endPoint = pointRepository.findOne(road.getEndPoint().getNodeId());

        road.setStartPoint(startPoint);
        road.setEndPoint(endPoint);


        if (startPoint.getRoads() == null) {
            startPoint.setRoads(new HashSet<>());
            startPoint.getRoads().add(road);
        } else
            startPoint.getRoads().add(road);

        pointRepository.save(startPoint);
        return road;
    }
}
