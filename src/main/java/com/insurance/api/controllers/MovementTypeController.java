package com.insurance.api.controllers;

import com.insurance.api.controllers.exception.NotExistingIdException;
import com.insurance.api.hateoas.assembler.MovementTypeModelAssembler;
import com.insurance.api.hateoas.model.MovementTypeModel;
import com.insurance.api.model.entities.Movement;
import com.insurance.api.model.entities.MovementType;
import com.insurance.api.model.entities.pojos.request.MovementTypePost;
import com.insurance.api.repository.MovementTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/movementType")
public class MovementTypeController {

    @Autowired
    MovementTypeRepository movementTypeRepo;
    @Autowired
    MovementTypeModelAssembler movementTypeModelAssembler;

    @GetMapping("")
    public ResponseEntity<CollectionModel<MovementTypeModel>> getAllMoveTypes() {
        List<MovementType> allMoveType = movementTypeRepo.findAll();
        return new ResponseEntity<>(movementTypeModelAssembler.toCollectionModel(allMoveType), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementTypeModel> getMoveTypeById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(movementTypeModelAssembler.toModel(
                movementTypeRepo.findByid(id)
                        .orElseThrow(() -> new NotExistingIdException(id))),
                HttpStatus.OK
        );
    }

    @PostMapping("")
    public MovementType insert(@RequestBody MovementTypePost movementTypePost) {
        MovementType movementType = new MovementType(movementTypePost.getName());
        return movementTypeRepo.save(movementType);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovementTypeModel> patch(@PathVariable("id") Long id, @RequestBody MovementTypePost patch) {
        MovementType movementType = movementTypeRepo.getOne(id);
        if (!movementType.getName().equals(patch.getName())) movementTypeRepo.changeName(id, patch.getName());
        return new ResponseEntity<>(movementTypeModelAssembler.toModel(movementType), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovementTypeModel> delete(@PathVariable("id") Long id) {
        MovementType movementType = movementTypeRepo.getOne(id);
        movementTypeRepo.delete(movementTypeRepo.getOne(id));
        return new ResponseEntity<>(movementTypeModelAssembler.toModel(movementType), HttpStatus.OK);
    }
}
