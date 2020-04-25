package com.insurance.api.hateoas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import java.util.List;

@JsonRootName(value = "movementType")
@Relation(collectionRelation = "movementType")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovementTypeModel extends RepresentationModel<MovementTypeModel> {

    Long id;
    String name;

    public MovementTypeModel() { super(); }

    public MovementTypeModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MovementTypeModel(Link initialLink, Long id, String name) {
        super(initialLink);
        this.id = id;
        this.name = name;
    }

    public MovementTypeModel(List<Link> initialLinks, Long id, String name) {
        super(initialLinks);
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MovementTypeModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public MovementTypeModel add(Link link) {
        return super.add(link);
    }

    public void add(WebMvcLinkBuilder linkTo) { }
}
