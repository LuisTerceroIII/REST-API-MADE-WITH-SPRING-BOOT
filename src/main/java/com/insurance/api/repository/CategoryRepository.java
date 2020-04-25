package com.insurance.api.repository;

import com.insurance.api.model.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // SELECTS ---------------------
    List<Category> findAll();// TODAS LAS CATEGORIAS

    Optional<Category> findByid(Long id);// DEVUELVE UN VALOR POR ID CON TIPO OPTIONAL PARA PODER LANZAR EXCEPCIONES CON .orElseThrow( () -> new Exception(args))

    @Query("select c from Category c where id = :id")
    Category getOneById(@Param("id") Long id);// CATEGORIA POR ID

    @Query(value = "SELECT * FROM CATEGORY ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Category getLast();//ULTIMO CATEGORIA DE LA LISTA
    //----
    //INSERT
    @Override
    <S extends Category> S save(S entity);//INSERT
    //-----
    // UPDATE
    @Modifying
    @Query("UPDATE Category set name = :name where id= :id")
    @Transactional
    void updateName(@Param("name") String name, @Param("id") Long id); //EDITAR / UPDATE :  NOMBRE DE CATEGORIA
    // -----
    // DELETE
    @Modifying
    @Query("delete Category where id = :id")
    @Transactional
    void delete(@Param("id") Long id);//BORRAR POR ID

    @Override
    void delete(Category entity); // Usar delete(Entity entity) para eliminar en controlador
}
