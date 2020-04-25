package com.insurance.api.hateoas.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@JsonRootName(value = "caategory")
@Relation(collectionRelation = "category")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryModel extends RepresentationModel<CategoryModel> {
    Long id;
    String name;

    public CategoryModel() {super();}

    public CategoryModel(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryModel(Link initialLink, Long id, String name) {
        super(initialLink);
        this.id = id;
        this.name = name;
    }

    public CategoryModel(List<Link> initialLinks, Long id, String name) {
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
        return "CategoryModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
