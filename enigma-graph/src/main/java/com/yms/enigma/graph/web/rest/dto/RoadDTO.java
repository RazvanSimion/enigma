package com.yms.enigma.graph.web.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yms.enigma.graph.domain.Point;
import com.yms.enigma.graph.domain.Road;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by Razvan.Simion on 4/19/2016.
 */
public class RoadDTO {
    private Long roadId;
    private Long answerId;
    private Long startPointNodeId;
    private Long endPointNodeId;

    public RoadDTO() {
    }

    public RoadDTO(Road road) {
        this.roadId = road.getRoadId();
        this.answerId = road.getAnswerId();
        this.startPointNodeId = road.getStartPoint().getNodeId();
        this.endPointNodeId = road.getEndPoint().getNodeId();
    }

    public RoadDTO(Long answerId, Long endPointNodeId, Long roadId, Long startPointNodeId) {
        this.answerId = answerId;
        this.endPointNodeId = endPointNodeId;
        this.roadId = roadId;
        this.startPointNodeId = startPointNodeId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getEndPointNodeId() {
        return endPointNodeId;
    }

    public void setEndPointNodeId(Long endPointNodeId) {
        this.endPointNodeId = endPointNodeId;
    }

    public Long getRoadId() {
        return roadId;
    }

    public void setRoadId(Long roadId) {
        this.roadId = roadId;
    }

    public Long getStartPointNodeId() {
        return startPointNodeId;
    }

    public void setStartPointNodeId(Long startPointNodeId) {
        this.startPointNodeId = startPointNodeId;
    }
}
