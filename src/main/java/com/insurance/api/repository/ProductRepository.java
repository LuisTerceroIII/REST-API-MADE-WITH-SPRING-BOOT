package com.insurance.api.repository;

import com.insurance.api.model.entities.Category;
import com.insurance.api.model.entities.Product;

import com.insurance.api.model.entities.pojos.ProductAndCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

// ------------- SELECT'S ----------------------

    @Query("select p from Product p order by id asc")
    List<Product> findAllOrderByid(); //TODOS LOS PRODUCTOS.

    Optional<Product> findByid(Long id);//PRODUCTO POR ID.

    @Query(value = "SELECT * from Product order by id desc limit 1", nativeQuery = true)
    Product getLast();//PRODUCTO CON MAS ELEMENTOS EN STOCK.

    @Query("select p from Product p where p.quantity > 0 order by quantity Asc")
    List<Product> inStock();//PRODUCTOS EN STOCK.

    @Query(value = "SELECT * from Product order by quantity desc limit 1", nativeQuery = true)
    Product maxInStock();//PRODUCTO CON MAS ELEMENTOS EN STOCK.

    @Query("Select p from Product p where p.category.id = :id order by p.quantity desc")
    List<Product> productsForCategory(@Param("id") Long id); //PRODUCTOS DE CADA CATEGORIA - INGRESAR ID DE CATEGORIA Y VER PRODUCTOS -.

    @Query("Select new com.insurance.api.model.entities.pojos.ProductAndCategory(count(p),c.name) from Product p Join p.category c where p.category.id = :id group by c.name")
    ProductAndCategory productsInCategory(@Param("id") Long id); //CANTIDAD DE PRODUCTOS POR ID DE CATEGORIA.

    @Query("Select new com.insurance.api.model.entities.pojos.ProductAndCategory(count(p) as count, c.name) from Product p Join p.category c group by c.name")
    List<ProductAndCategory>productsByCategory();//CANTIDAD DE PRODUCTOS SEPARADOS EN CATEGORIA.

// -------------------- END SELECT'S -------------------------------------

// ------------------- INSERT , UPDATE AND DELETE -------------------------
    // INSERT
    @Override // LOS INSERTS SE REALIZAN CON EL METODO save(e Entity) DE JPA.
    <S extends Product> S save(S entity);//INSERT - UNICO VALOR NO NULL = p.category.

    // UPDATES
    @Modifying
    @Query("UPDATE Product p set p.category = :category, p.brand = :brand, p.name = :name, p.unity = :unity, p.quantity = :quantity where p.id = :id")
    @Transactional
    void overWrite(@Param("id") Long id,
                   @Param("category") Category category,
                   @Param("brand") String brand,
                   @Param("name") String name,
                   @Param("unity") String unity,
                   @Param("quantity") int quantity); // CAMBIAR TODOS LOS ATRIBUTOS DE PRODUCTO, SALVO SU ID.

    @Modifying
    @Query("UPDATE Product p set p.category = :category where p.id = :id")
    @Transactional
    void changeCategory(@Param("id") Long id , @Param("category") Category category); //CAMBIAR PRODUCTO DE CATEGORIA.

    @Modifying
    @Query("UPDATE Product p set p.brand = :brand where p.id = :id")
    @Transactional
    void changeBrand(@Param("id") Long id , @Param("brand") String brand); //CAMBIAR MARCA DE PRODUCTO.

    @Modifying
    @Query("UPDATE Product p set p.name = :name where p.id = :id")
    @Transactional
    void changeName(@Param("id") Long id , @Param("name") String name); //CAMBIAR NOMBRE DE PRODUCTO.

    @Modifying
    @Query("UPDATE Product p set p.unity = :unity where p.id = :id")
    @Transactional
    void changeUnity(@Param("id") Long id , @Param("unity") String unity); //CAMBIAR DESCRIPCION DE CONTENIDO DE PRODUCTO.

    @Modifying
    @Query("UPDATE Product p set p.quantity = :quantity where p.id = :id")
    @Transactional
    void changeQuantity(@Param("id") Long id , @Param("quantity") int quanity); //CAMBIAR NOMBRE DE PRODUCTO.

    //-------------------------------------------------------------------------

    // DELETE
    @Modifying
    @Query("delete Product where id = :id")
    @Transactional
    void deleteById(@Param("id")Long id);//BORRAR POR ID.

    @Modifying
    @Query("delete Product where brand like concat('%',:brand,'%')")
    @Transactional
    void deleteByBrand(@Param("brand")String brand);//BORRAR POR MARCA.

    @Modifying
    @Query("delete Product where name like concat('%',:name,'%')")
    @Transactional
    void deleteByName(@Param("name")String name);//BORRAR POR NOMBRE.

    @Modifying
    @Query("delete Product where unity like concat('%',:unity,'%')")
    @Transactional
    void deleteByUnity(@Param("unity")String unity);//BORRAR POR DESCRIPCIPON DE CANTIDAD

    @Modifying
    @Query("delete Product where quantity = :quantity")
    @Transactional
    void deleteByQuantity(@Param("quantity")int quantity);//BORRAR POR CANTIDAD

    @Override
    void delete(Product entity); // Usar delete(Entity entity) para eliminar en controlador
}
