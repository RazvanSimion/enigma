package com.yms.enigma.graph.domain;

import org.neo4j.ogm.annotation.*;

import java.util.Date;

/**
 * Created by Razvan.Simion on 4/18/2016.
 */
@RelationshipEntity(type = "CURRENT_POSITION")
public class CurrentPosition {
    @GraphId
    private Long relationshipId;
    @Property
    private Date startDate;
    @Property
    private Date EndDate;
    @StartNode
    private Person person;
    @EndNode
    private Point point;

    public CurrentPosition() {
    }

    public CurrentPosition(Date endDate, Person person, Point point, Long relationshipId, Date startDate) {
        EndDate = endDate;
        this.person = person;
        this.point = point;
        this.relationshipId = relationshipId;
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(Date endDate) {
        EndDate = endDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
