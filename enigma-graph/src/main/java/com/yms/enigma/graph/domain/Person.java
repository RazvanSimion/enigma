package com.yms.enigma.graph.domain;

import org.neo4j.ogm.annotation.*;

import java.util.Date;
import java.util.Set;

/**
 * Created by Razvan.Simion on 4/18/2016.
 */
@NodeEntity
public class Person {

    @GraphId
    Long nodeId;

    String username;
    String firstName;
    String lastName;
    Long avatarId;

    @Relationship(type = "CURRENT_POSITION", direction = Relationship.OUTGOING)
    CurrentPosition currentPosition;

    @Relationship(type = "PREVIOUS_POSITION", direction = Relationship.OUTGOING)
    Set<PreviousPosition> previousPositions;


    public Person() {
    }

    public Person(Long avatarId, String firstName, String lastName, Long nodeId, String username) {
        this.avatarId = avatarId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nodeId = nodeId;
        this.username = username;
    }

    public Person(Long avatarId, String firstName, String lastName, String username) {
        this.avatarId = avatarId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
    }

    public Long getAvatarId() {

        return avatarId;
    }

    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    public CurrentPosition getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(CurrentPosition currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Set<PreviousPosition> getPreviousPositions() {
        return previousPositions;
    }

    public void setPreviousPositions(Set<PreviousPosition> previousPositions) {
        this.previousPositions = previousPositions;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
