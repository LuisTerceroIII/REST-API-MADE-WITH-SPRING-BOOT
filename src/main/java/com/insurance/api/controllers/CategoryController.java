package com.insurance.api.controllers;

import com.insurance.api.controllers.exception.NotExistingIdException;
import com.insurance.api.hateoas.assembler.CategoryModelAssembler;
import com.insurance.api.hateoas.model.CategoryModel;
import com.insurance.api.model.entities.Category;
import com.insurance.api.model.entities.pojos.request.CategoryPost;
import com.insurance.api.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepo;
    @Autowired
    private CategoryModelAssembler categoryModelAssembler;

    @GetMapping("")
    public ResponseEntity<CollectionModel<CategoryModel>> getAllCategory() {
        List<Category> categorys = categoryRepo.findAll();
        return new ResponseEntity<>(categoryModelAssembler.toCollectionModel(categorys), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> getCategoryById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(
                categoryModelAssembler.toModel(
                        categoryRepo.findByid(id).orElseThrow(() -> new NotExistingIdException(id)))
                , HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CategoryModel> postCategory(@RequestBody CategoryPost categoryPost) {
        Category category = new Category(categoryPost.getName());
        return new ResponseEntity<>(categoryModelAssembler.toModel(categoryRepo.save(category)), HttpStatus.OK);
    }

    @PatchMapping("/{id}") //RETORNA EL ELEMETO ACTUALIZADO
    public ResponseEntity<CategoryModel> patch(@PathVariable("id") Long id, @RequestBody CategoryPost patch) {
        Category category = categoryRepo.getOneById(id);
        if (!category.getName().equals(patch.getName())) categoryRepo.updateName(patch.getName(), id);
        return new ResponseEntity<>(categoryModelAssembler.toModel(category), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoryModel> delete(@PathVariable("id") Long id) {
        Category category = categoryRepo.getOneById(id);
        categoryRepo.delete(id);
        return new ResponseEntity<>(categoryModelAssembler.toModel(category), HttpStatus.OK);
    }
}
