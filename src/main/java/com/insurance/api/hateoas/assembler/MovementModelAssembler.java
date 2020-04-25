package com.insurance.api.hateoas.assembler;

import com.insurance.api.controllers.*;
import com.insurance.api.hateoas.model.MovementModel;
import com.insurance.api.model.entities.Movement;
import com.insurance.api.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class MovementModelAssembler extends RepresentationModelAssemblerSupport<Movement, MovementModel> {

    @Autowired
    MovementRepository movementRepo;

    public MovementModelAssembler() {
        super(MovementController.class, MovementModel.class);
    }

    public MovementModelAssembler(Class<?> controllerClass, Class<MovementModel> resourceType) {
        super(controllerClass, resourceType);
    }

    @Override
    public MovementModel toModel(Movement movement) {

        MovementModel movementModel = instantiateModel(movement);
        Link start = linkTo(methodOn(StartPointController.class).allCollections()).withRel("Start");
        Link self = linkTo(methodOn(MovementController.class).getMoveById(movement.getId())).withSelfRel();
        Link products = linkTo(methodOn(ProductController.class).getProductById((long) movement.getId_product())).withRel("Product in movement");
        Link movementType = linkTo(methodOn(MovementTypeController.class).getMoveTypeById(movement.getId_movement_type().getId())).withRel("Movement Type of Movement");
        Link previuos = linkTo(methodOn(MovementController.class).getMoveById(movement.getId() - 1)).withSelfRel().withRel("Previous");
        Link next = linkTo(methodOn(MovementController.class).getMoveById(movement.getId() + 1)).withSelfRel().withRel("Next");
        Link collectionMovement = linkTo(methodOn(MovementController.class).getAllMoves()).withRel("Collection");
        Link category = linkTo(methodOn(CategoryController.class).getAllCategory()).withSelfRel().withRel("Collection");
        Link product = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).getProducts()).withSelfRel().withRel("Collection");
        Link movementTypeCollection = linkTo(methodOn(MovementTypeController.class).getAllMoveTypes()).withRel("Collection");
        Link first = linkTo(methodOn(MovementController.class).getMoveById(1L)).withRel("First");
        Link last = linkTo(methodOn(MovementController.class).getMoveById(movementRepo.getLast().getId())).withRel("Last");

        movementModel.setId(movement.getId());
        movementModel.setMovementTypeId(movement.getId_movement_type().getId());
        movementModel.setProductId(movement.getId_product());
        movementModel.setQuantity(movement.getQuantity());
        movementModel.setPriceUnit(movement.getPrice_unit());
        movementModel.setDate(movement.getDate());
        movementModel.setTotal(movement.getTotal());

        movementModel.add(start);
        movementModel.add(self);
        movementModel.add(products);
        movementModel.add(movementType);
        movementModel.add(previuos);
        movementModel.add(next);
        movementModel.add(collectionMovement);
        movementModel.add(movementTypeCollection,category,product);
        movementModel.add(first);
        movementModel.add(last);


        return movementModel;
    }

    @Override
    public CollectionModel<MovementModel> toCollectionModel(Iterable<? extends Movement> movements) {
        CollectionModel<MovementModel> movementModels = super.toCollectionModel(movements);
        movementModels.add(
                linkTo(methodOn(MovementController.class).getAllMoves())
                        .withSelfRel()
        );

        return movementModels;
    }

    @Override
    protected MovementModel createModelWithId(Object id, Movement entity) {
        return super.createModelWithId(id, entity);
    }

    @Override
    protected MovementModel createModelWithId(Object id, Movement entity, Object... parameters) {
        return super.createModelWithId(id, entity, parameters);
    }

    @Override
    protected Class<?> getControllerClass() {
        return super.getControllerClass();
    }

    @Override
    protected Class<MovementModel> getResourceType() {
        return super.getResourceType();
    }

    @Override
    protected MovementModel instantiateModel(Movement entity) {
        return super.instantiateModel(entity);
    }

}
