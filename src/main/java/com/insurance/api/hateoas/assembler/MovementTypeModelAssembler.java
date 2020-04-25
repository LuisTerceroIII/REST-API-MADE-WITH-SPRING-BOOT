package com.insurance.api.hateoas.assembler;

import com.insurance.api.controllers.*;
import com.insurance.api.hateoas.model.MovementTypeModel;
import com.insurance.api.model.entities.MovementType;
import com.insurance.api.repository.MovementTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class MovementTypeModelAssembler extends RepresentationModelAssemblerSupport<MovementType, MovementTypeModel> {

    @Autowired
    private MovementTypeRepository movementTypeRepository;

    public MovementTypeModelAssembler(Class<?> controllerClass, Class<MovementTypeModel> resourceType) {
        super(controllerClass, resourceType);
    }

    public MovementTypeModelAssembler() {
        super(MovementTypeController.class, MovementTypeModel.class);
    }

    @Override
    public CollectionModel<MovementTypeModel> toCollectionModel(Iterable<? extends MovementType> movementTypes) {
        CollectionModel<MovementTypeModel> movementTypeModels = super.toCollectionModel(movementTypes);
        movementTypeModels.add(
                linkTo(methodOn(MovementTypeController.class).getAllMoveTypes()).withSelfRel()
        );
        return movementTypeModels;
    }

    @Override
    public MovementTypeModel toModel(MovementType movementType) {

        MovementTypeModel movementTypeModel = instantiateModel(movementType);
        Link start = linkTo(methodOn(StartPointController.class).allCollections()).withRel("Start");
        Link self = linkTo(methodOn(MovementTypeController.class).getMoveTypeById(movementType.getId())).withSelfRel();
        Link previuos = linkTo(methodOn(MovementTypeController.class).getMoveTypeById(movementType.getId()-1)).withSelfRel().withRel("Previous");
        Link next = linkTo(methodOn(MovementTypeController.class).getMoveTypeById(movementType.getId()+1)).withSelfRel().withRel("Next");
        Link movementTypeCollection = linkTo(methodOn(MovementTypeController.class).getAllMoveTypes()).withRel("Collection");
        Link product = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(ProductController.class).getProducts()).withSelfRel().withRel("Collection");
        Link movement = linkTo(methodOn(MovementController.class).getAllMoves()).withRel("Collection");
        Link category = linkTo(methodOn(CategoryController.class).getAllCategory()).withSelfRel().withRel("Collection");
        Link first = linkTo(methodOn(MovementTypeController.class).getMoveTypeById(1L)).withSelfRel().withRel("First");
        Link last = linkTo(methodOn(MovementTypeController.class).getMoveTypeById(movementTypeRepository.getLast().getId())).withSelfRel().withRel("Last");
        movementTypeModel.setId(movementType.getId());
        movementTypeModel.setName(movementType.getName());

        movementTypeModel.add(start);
        movementTypeModel.add(self);
        movementTypeModel.add(previuos);
        movementTypeModel.add(next);
        movementTypeModel.add(movementTypeCollection,movement,category,product);
        movementTypeModel.add(first);
        movementTypeModel.add(last);

        return movementTypeModel;
    }

/*    @Override
    public RepresentationModelAssemblerSupport.Builder<MovementType, MovementTypeModel> map(Iterable<? extends MovementType> entities) {
        return super.map(entities);
    }*/

    @Override
    protected MovementTypeModel createModelWithId(Object id, MovementType entity) {
        return super.createModelWithId(id, entity);
    }

    @Override
    protected MovementTypeModel createModelWithId(Object id, MovementType entity, Object... parameters) {
        return super.createModelWithId(id, entity, parameters);
    }

    @Override
    protected Class<?> getControllerClass() {
        return super.getControllerClass();
    }

    @Override
    protected Class<MovementTypeModel> getResourceType() {
        return super.getResourceType();
    }

    @Override
    protected MovementTypeModel instantiateModel(MovementType entity) {
        return super.instantiateModel(entity);
    }


}
