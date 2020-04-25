package com.insurance.api.hateoas.assembler;


import com.insurance.api.controllers.*;
import com.insurance.api.hateoas.model.ProductModel;
import com.insurance.api.model.entities.Product;

import com.insurance.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductModel> {

    @Autowired
    ProductRepository productRepo;

    public ProductModelAssembler(Class<?> controllerClass, Class<ProductModel> resourceType) {
        super(controllerClass, resourceType);
    }

    public ProductModelAssembler() {
        super(ProductController.class, ProductModel.class);
    }

    @Override
    public CollectionModel<ProductModel> toCollectionModel(Iterable<? extends Product> entities) {
        CollectionModel<ProductModel> productModels = super.toCollectionModel(entities);
        productModels.add(
                linkTo(methodOn(ProductController.class)
                        .getProducts())
                        .withSelfRel()
                        .withRel("product")
        );

        return productModels;
    }
    @Override
    public ProductModel toModel(Product entity) {
        ProductModel productModel = instantiateModel(entity);

        Link start = linkTo(methodOn(StartPointController.class).allCollections()).withRel("Start");

        Link toProducts = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class)
                .getProducts())
                .withSelfRel().withRel("Collection");
        Link toPreviues = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class)
                .getProductById(entity.getId()-1))
                .withSelfRel()
                .withRel("Previues");
        Link toNext = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class)
                .getProductById(entity.getId() + 1))
                .withSelfRel()
                .withRel("Next");

        Link category = WebMvcLinkBuilder.linkTo(methodOn(CategoryController.class)
                .getCategoryById(entity.getCategory().getId()))
                .withSelfRel()
                .withRel("Category");
        Link movementType = linkTo(methodOn(MovementTypeController.class).getAllMoveTypes()).withRel("Collection");
        Link movement = linkTo(methodOn(MovementController.class).getAllMoves()).withRel("Collection");
        Link categoryCollection = linkTo(methodOn(CategoryController.class).getAllCategory()).withSelfRel().withRel("Collection");
        Link first = linkTo(methodOn(ProductController.class).getProductById(1L)).withSelfRel().withRel("First");
        Link last = linkTo(methodOn(ProductController.class).getProductById(productRepo.getLast().getId())).withSelfRel().withRel("Last");

        productModel.add(start);
        productModel.add(
                linkTo(methodOn(ProductController.class)
                        .getProductById(entity.getId()))
                        .withSelfRel()

        );
        productModel.add(category);
        productModel.add(toPreviues);
        productModel.add(toNext);
        productModel.add(toProducts,categoryCollection,movementType,movement);
        productModel.add(first);
        productModel.add(last);


        productModel.setId(entity.getId());
        productModel.setBrand(entity.getBrand());
        productModel.setName(entity.getName());
        productModel.setUnity(entity.getUnity());
        productModel.setQuantity(entity.getQuantity());
        productModel.setIdCategory(entity.getCategory().getId());
        productModel.setNameCategory(entity.getCategory().getName());
        //productModel.setCategory(entity.getCategory());

        return productModel;
    }

/*
    public RepresentationModelAssemblerSupport.Builder<Product, ProductModel> map(Iterable<? extends Product> entities) {
        return super.map(entities);
    }
*/

    @Override
    protected ProductModel createModelWithId(Object id, Product entity) {
        return super.createModelWithId(id, entity);
    }

    @Override
    protected ProductModel createModelWithId(Object id, Product entity, Object... parameters) {
        return super.createModelWithId(id, entity, parameters);
    }

    @Override
    protected Class<?> getControllerClass() {
        return super.getControllerClass();
    }

    @Override
    protected Class<ProductModel> getResourceType() {
        return super.getResourceType();
    }

    @Override
    protected ProductModel instantiateModel(Product entity) {
        return super.instantiateModel(entity);
    }


}
