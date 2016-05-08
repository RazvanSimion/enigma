package com.yms.enigma.graph.repository;

import com.yms.enigma.graph.domain.Point;
import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by Razvan.Simion on 4/17/2016.
 */
public interface PointRepository extends GraphRepository<Point> {
    //Point findById(Long nodeId);
}
