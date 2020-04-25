package com.insurance.api.controllers;

import com.insurance.api.controllers.exception.NotExistingIdException;
import com.insurance.api.hateoas.assembler.MovementModelAssembler;
import com.insurance.api.hateoas.model.MovementModel;
import com.insurance.api.model.entities.Movement;
import com.insurance.api.model.entities.MovementType;
import com.insurance.api.model.entities.Product;
import com.insurance.api.model.entities.pojos.request.MovementPost;
import com.insurance.api.repository.MovementRepository;
import com.insurance.api.repository.MovementTypeRepository;
import com.insurance.api.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/movement")
public class MovementController {
    Logger logger = LoggerFactory.getLogger(MovementController.class);
    @Autowired
    MovementRepository movementRepo;
    @Autowired
    MovementTypeRepository movementTypeRepo;
    @Autowired
    ProductRepository productRepo;
    @Autowired
    MovementModelAssembler movementModelAssembler;


    @GetMapping("")
    public ResponseEntity<CollectionModel<MovementModel>> getAllMoves() {
        List<Movement> movements = movementRepo.findAllOrderBydate();
        return new ResponseEntity<>(movementModelAssembler.toCollectionModel(movements), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovementModel> getMoveById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                movementModelAssembler.toModel(movementRepo.findByid(id)
                        .orElseThrow(() -> new NotExistingIdException(id))),
                HttpStatus.OK
        );
    }

    @PostMapping("")
    public ResponseEntity<MovementModel> insert(@RequestBody MovementPost movementPost) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(movementPost.getDate());
        Movement movement = new Movement(movementTypeRepo.getOne(movementPost.getMovementTypeId()),
                movementPost.getProductId().intValue(),
                movementPost.getQuantity(),
                movementPost.getPriceUnit(),
                date,
                movementPost.getTotal());
        return new ResponseEntity<>(movementModelAssembler.toModel(movementRepo.save(movement)), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<MovementModel> patch(@PathVariable("id") Long id, @RequestBody MovementPost patch) throws ParseException {
        Movement movement = movementRepo.getOne(id);
        logger.info(movement+"");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(patch.getDate());
        if (!movement.getId_movement_type().getId().equals(patch.getMovementTypeId()) && patch.getMovementTypeId() != null) movementRepo.changeMovemetType(id,movementTypeRepo.getOne(patch.getMovementTypeId()));
        if ( patch.getProductId() != null && movement.getId_product() != patch.getProductId()) movementRepo.changeIdProduct(id, patch.getProductId().intValue()); //funciona porque no existe los id valor 0, son a contar de 1.
        if(patch.getPriceUnit() != null && movement.getPrice_unit() != patch.getPriceUnit()) movementRepo.changePriceUnit(id,patch.getPriceUnit());
        if (patch.getQuantity() != null && movement.getQuantity() != patch.getQuantity()) movementRepo.changeQuantity(id, patch.getQuantity());
        if (movement.getDate() == null || !movement.getDate().equals(date)) movementRepo.changeDate(id, date);
        if (patch.getTotal() != null && movement.getTotal() != patch.getTotal()) movementRepo.changeTotal(id, patch.getTotal());
        return new ResponseEntity<>(movementModelAssembler.toModel(movement), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MovementModel> delete(@PathVariable("id") Long id) {
        Movement movement = movementRepo.getOne(id);
        movementRepo.delete(movementRepo.getOne(id));
        return new ResponseEntity<>(movementModelAssembler.toModel(movement), HttpStatus.OK);
    }


}
