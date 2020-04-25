package com.insurance.api.repository;


import com.insurance.api.model.entities.MovementType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MovementTypeRepository extends JpaRepository<MovementType,Long> {
    //Todos
    List<MovementType> findAll();
    //Por id
    Optional<MovementType> findByid(Long id);

    @Query(value = "SELECT * FROM MovementType ORDER BY id DESC LIMIT 1",nativeQuery = true)
    MovementType getLast();
    //Insert
    @Override
    <S extends MovementType> S save(S entity);
    //Update name
    @Modifying
    @Query("UPDATE MovementType set name = :name where id= :id")
    @Transactional
    void changeName(@Param("id") Long id, @Param("name") String name);
    //delete
    @Modifying
    @Query("delete MovementType where id = :id")
    @Transactional
    void delete(@Param("id")Long id);

    @Override
    void delete(MovementType entity); // Usar delete(Entity entity) para eliminar en controlador
}
