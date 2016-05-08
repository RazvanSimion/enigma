package com.yms.enigma.graph.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

/**
 * Created by Razvan.Simion on 4/18/2016.
 */
@RelationshipEntity(type = "HAS_ROAD_TO")
public class Road {
    @GraphId
    private Long roadId;
    @Property
    private Long answerId;


    //@JsonIgnore
    @StartNode
    private Point startPoint;

    //@JsonIgnore
    @EndNode
    private Point endPoint;


    public Road(Long answerId, Point endPoint, Long roadId, Point startPoint) {
        this.answerId = answerId;
        this.endPoint = endPoint;
        this.roadId = roadId;
        this.startPoint = startPoint;
    }

    public Road() {
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public Long getRoadId() {
        return roadId;
    }

    public void setRoadId(Long roadId) {
        this.roadId = roadId;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }
}
