package com.insurance.api.hateoas.assembler;

import com.insurance.api.controllers.*;
import com.insurance.api.hateoas.model.CategoryModel;
import com.insurance.api.model.entities.Category;
import com.insurance.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CategoryModelAssembler extends RepresentationModelAssemblerSupport<Category, CategoryModel> {

    @Autowired
    CategoryRepository categoryRepo;
    /**
     * Creates a new {@link RepresentationModelAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public CategoryModelAssembler(Class<?> controllerClass, Class<CategoryModel> resourceType) {
        super(controllerClass, resourceType);
    }

    public CategoryModelAssembler() {//OBLIGATORIO ! CON EL IMPLEMENTAMOS EL ENSAMBLADOR!
        super(CategoryController.class, CategoryModel.class);
    }

    @Override
    public CategoryModel toModel(Category category) {
        System.out.println("" + category.getId());
        CategoryModel categoryModel = instantiateModel(category);
        Link product = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).getProducts()).withSelfRel().withRel("Collection");
        Link movementType = linkTo(methodOn(MovementTypeController.class).getAllMoveTypes()).withRel("Collection");
        Link movement = linkTo(methodOn(MovementController.class).getAllMoves()).withRel("Collection");
        categoryModel.add(linkTo(methodOn(StartPointController.class).allCollections()).withRel("Start point"));

        categoryModel.add(
                linkTo(methodOn(CategoryController.class)
                        .getCategoryById(category.getId()))
                        .withSelfRel()
        );
        categoryModel.add(
                linkTo(methodOn(CategoryController.class)
                        .getCategoryById(category.getId() - 1))
                        .withSelfRel()
                        .withRel("Previues")
        );
        categoryModel.add(
                linkTo(methodOn(CategoryController.class)
                        .getCategoryById(category.getId() + 1))
                        .withSelfRel()
                        .withRel("Next")
        );
        categoryModel.add(
                linkTo(methodOn(CategoryController.class)
                        .getAllCategory())
                        .withSelfRel()
                        .withRel("Collection")
        );
        categoryModel.add(product,movementType,movement);


        categoryModel.add(linkTo(methodOn(CategoryController.class).getCategoryById(1L)).withRel("First"));
        categoryModel.add(linkTo(methodOn(CategoryController.class).getCategoryById(categoryRepo.getLast().getId())).withRel("Last"));



        categoryModel.setId(category.getId());
        categoryModel.setName(category.getName());
        return categoryModel;
    }
    @Override
    public CollectionModel<CategoryModel> toCollectionModel(Iterable<? extends Category> listOfCatgeory) {
        CollectionModel<CategoryModel> categorys = super.toCollectionModel(listOfCatgeory);
        categorys.add(linkTo(methodOn(CategoryController.class)
                .getAllCategory()).withSelfRel());
        return categorys;
    }
}
