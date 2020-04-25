package com.insurance.api.repository;

import com.insurance.api.model.entities.Movement;
import com.insurance.api.model.entities.MovementType;
import com.insurance.api.model.entities.Product;
import com.insurance.api.model.entities.pojos.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MovementRepository extends JpaRepository<Movement,Long> {

    @Query("select m from Movement m order by m.date desc")
    List<Movement> findAllOrderBydate(); // TODOS LOS MOVIMIENTOS.

    Optional<Movement> findByid(Long id);// MOVIMIENTO POR ID.

    @Override
    Movement getOne(Long id);

    @Query(value = "SELECT * FROM MOVEMENT ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Movement getLast();

    @Query("select m from Movement m where m.id_movement_type=1 order by m.date")
    List<Movement> historicalPucharses();// TODOS LOS MOVIMIENTOS DE ALTA.

    @Query("select m from Movement m where m.id_movement_type=2 order by m.date")
    List<Movement> historicalSales();// TODOS LOS MOVIMIENTOS DE BAJA.

    @Query("select m from Movement m where m.id_movement_type.id=1 and SubString(cast(m.date as text),1,4) = :year")
    List<Movement> buysByYear(@Param("year") String year);// ALTAS POR AÑO.

    @Query("select m from Movement m where m.id_movement_type.id=2 and SubString(cast(m.date as text),1,4) = :year")
    List<Movement> sellsByYear(@Param("year") String year);// BAJAS POR AÑO.

    @Query("Select " +
            "new com.insurance.api.model.entities.pojos.Balance(" +
            "(select Sum(m.total) from Movement m where m.id_movement_type.id=1) as Invested" +
            "," +
            "(select sum(m.total) from Movement m where m.id_movement_type.id=2) as Earned) from Movement where id=1 ")
    Balance balance();// BALANCE DE FINANZAS.
    // -------------------------------------

    // ------------ INSERT ----
    @Override // LOS INSERTS SE REALIZAN CON EL METODO save(e Entity) DE JPA.
    <S extends Movement> S save(S entity);// INSERTAR NUEVO MOVIMIEMTO

    // --------- UPDATES ---
    @Modifying
    @Query("update Movement m set m.id_movement_type = :movementType where m.id= :id")
    @Transactional
    void changeMovemetType(@Param("id") Long id, @Param("movementType") MovementType movementType); // UPDATE MOVEMENT TYPE

    @Modifying
    @Query("UPDATE Movement m set m.id_product = :id_product where id = :id")
    @Transactional
    void changeIdProduct(@Param("id") Long id, @Param("id_product") int id_product); // UPDATE ID PRODUCT

    @Modifying
    @Query("UPDATE Movement m set m.quantity = :quantity where id= :id")
    @Transactional
    void changeQuantity(@Param("id")Long id, @Param("quantity") int quantity); // UPDATE QUANTITY

    @Modifying
    @Query("UPDATE Movement m set m.price_unit = :priceUnit where id= :id")
    @Transactional
    void changePriceUnit(@Param("id")Long id, @Param("priceUnit") int priceUnit); // UPDATE PRICE UNIT

    @Modifying
    @Query("UPDATE Movement m set m.date = :date where id= :id")
    @Transactional
    void changeDate(@Param("id")Long id, @Param("date") Date date); // UPDATE DATE

    @Modifying
    @Query("UPDATE Movement m set m.total = :total where id= :id")
    @Transactional
    void changeTotal(@Param("id")Long id, @Param("total") int total); // UPDATE TOTAL


    // ----------- DELETE'S -----
    @Modifying
    @Query("delete Movement m where m.id = :id")
    @Transactional
    void deleteById(@Param("id")Long id);

    @Override
    void delete(Movement entity); // Usar delete(Entity entity) para eliminar en controlador
}
