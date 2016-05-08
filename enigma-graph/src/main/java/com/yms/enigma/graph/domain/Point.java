package com.yms.enigma.graph.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.neo4j.ogm.annotation.*;

import java.util.Date;
import java.util.Set;

/**
 * Created by Razvan.Simion on 4/17/2016.
 */
@NodeEntity
public class Point {

    @GraphId
    Long nodeId;

    @Relationship(type = "HAS_ROAD_TO", direction = Relationship.OUTGOING)
    Set<Road> roads;

    String name;
    Long queryId;

    double lat;
    double lng;

    public Point(double lat, double lng, String name, Long nodeId, Long queryId) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.nodeId = nodeId;
        this.queryId = queryId;
    }

    public Point(double lat, double lng, String name, Long queryId) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.queryId = queryId;
    }

    public Point(String name, Long nodeId, Long queryId, Set<Road> roads) {
        this.name = name;
        this.nodeId = nodeId;
        this.queryId = queryId;
        this.roads = roads;
    }

    public Point(String name, Long queryId) {
        this.name = name;
        this.queryId = queryId;
    }

    public Point(String name, Long nodeId, Long queryId) {
        this.name = name;
        this.nodeId = nodeId;
        this.queryId = queryId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Set<Road> getRoads() {
        return roads;
    }

    public void setRoads(Set<Road> roads) {
        this.roads = roads;
    }

    public Point() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getQueryId() {
        return queryId;
    }

    public void setQueryId(Long queryId) {
        this.queryId = queryId;
    }
}
