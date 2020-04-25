package com.insurance.api.controllers;

import com.insurance.api.repository.CategoryRepository;
import com.insurance.api.repository.MovementRepository;
import com.insurance.api.repository.MovementTypeRepository;
import com.insurance.api.repository.ProductRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api")
public class StartPointController {

    @Autowired
    CategoryRepository categoryRepo;
    @Autowired
    ProductRepository productRepo;
    @Autowired
    MovementTypeRepository movementTypeRepo;
    @Autowired
    MovementRepository movementRepo;
    @Autowired
    Api.ApiModel.ApiModelAssembler apiModelAssembler;

    static
    class Api {
        static
        class ApiModel extends RepresentationModel<ApiModel> {
            @Component
            static
            class ApiModelAssembler extends RepresentationModelAssemblerSupport<Api,ApiModel> {

                public ApiModelAssembler() {
                    super(StartPointController.class, ApiModel.class);
                }
                @Override
                public ApiModel toModel(Api api) {
                    ApiModel apiModel = instantiateModel(api);
                    Link category = WebMvcLinkBuilder.linkTo(
                            methodOn(CategoryController.class).getAllCategory())
                            .withSelfRel().withRel("Category");
                    Link products = WebMvcLinkBuilder.linkTo(
                            methodOn(ProductController.class).getProducts())
                            .withSelfRel().withRel("Product");
                    Link movementTypes = WebMvcLinkBuilder.linkTo(
                            methodOn(MovementTypeController.class).getAllMoveTypes())
                            .withRel("Movement Type");
                    Link movement = WebMvcLinkBuilder.linkTo(
                            methodOn(MovementController.class).getAllMoves())
                            .withRel("Movement");
                    apiModel.add(category);
                    apiModel.add(products);
                    apiModel.add(movementTypes);
                    apiModel.add(movement);
                    return apiModel;
                }
                @Override
                public CollectionModel<ApiModel> toCollectionModel(Iterable<? extends Api> collections) {
                    CollectionModel<ApiModel> apiModels = super.toCollectionModel(collections);
                    apiModels.add(
                            linkTo(methodOn(StartPointController.class).allCollections())
                            .withSelfRel()
                    );
                    return apiModels;
                }

            }
        }
    }

    @GetMapping("")
    public ResponseEntity<Api.ApiModel> allCollections() {
        Api api = new Api();
        return ResponseEntity.ok().body(apiModelAssembler.toModel(api));
    }

    
}
