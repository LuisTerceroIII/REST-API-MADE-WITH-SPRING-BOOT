package com.insurance.api.controllers;

import com.insurance.api.controllers.exception.NotExistingIdException;
import com.insurance.api.hateoas.assembler.ProductModelAssembler;
import com.insurance.api.hateoas.model.ProductModel;
import com.insurance.api.model.entities.Product;
import com.insurance.api.model.entities.pojos.request.ProductPost;
import com.insurance.api.repository.CategoryRepository;
import com.insurance.api.repository.ProductRepository;
import com.insurance.api.services.implement.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductModelAssembler productModelAssembler;

    @GetMapping("")
    public ResponseEntity<CollectionModel<ProductModel>> getProducts() {
        List<Product> products = productRepo.findAllOrderByid();
        return new ResponseEntity<>(productModelAssembler.toCollectionModel(products), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getProductById(@PathVariable("id") Long id) {
        return new ResponseEntity<ProductModel>(productModelAssembler.toModel(
                productRepo.findByid(id)
                        .orElseThrow(() -> new NotExistingIdException(id))),
                HttpStatus.OK
        );
    }

    @PostMapping("")
    public ResponseEntity<ProductModel> insert(@RequestBody ProductPost productPost) {
        Product product = new Product(categoryRepo.getOneById(productPost.getId_category()), productPost.getBrand(), productPost.getName(), productPost.getUnity(), productPost.getQuantity());
        return new ResponseEntity<>(productModelAssembler.toModel(productRepo.save(product)), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProductModel> patch(@PathVariable("id") Long id, @RequestBody ProductPost patch) {
        Product product = productRepo.getOne(id);
        if (!product.getCategory().getId().equals(patch.getId_category()) && patch.getId_category() != null)
            productRepo.changeCategory(id, categoryRepo.getOneById(patch.getId_category()));
        System.out.println(product.getName().equals(patch.getName()));
        if (!product.getName().equals(patch.getName()) && patch.getName() != null)
            productRepo.changeName(id, patch.getName());
        if (!product.getBrand().equals(patch.getBrand()) && patch.getBrand() != null) productRepo.changeBrand(id, patch.getBrand());
        if (!product.getUnity().equals(patch.getUnity()) && patch.getUnity() != null) productRepo.changeUnity(id, patch.getUnity());
        if (product.getQuantity() != patch.getQuantity()) productRepo.changeQuantity(id, patch.getQuantity());
        return new ResponseEntity<>(productModelAssembler.toModel(productRepo.findByid(id).orElseThrow(() -> new NotExistingIdException(id))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductModel> delete(@PathVariable("id") Long id) {
        Product product = productRepo.getOne(id);
        productRepo.delete(productRepo.getOne(id));
        return new ResponseEntity<>(productModelAssembler.toModel(product), HttpStatus.OK);
    }

}
