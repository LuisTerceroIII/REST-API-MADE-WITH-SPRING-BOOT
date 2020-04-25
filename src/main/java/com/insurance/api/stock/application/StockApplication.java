package com.insurance.api.stock.application;

import com.insurance.api.hateoas.assembler.ProductModelAssembler;

import com.insurance.api.model.entities.Movement;
import com.insurance.api.model.entities.Product;
import com.insurance.api.repository.CategoryRepository;
import com.insurance.api.repository.MovementRepository;
import com.insurance.api.repository.MovementTypeRepository;
import com.insurance.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories({"com.insurance.api.repository"})
@ComponentScan(basePackages = {"com.insurance.api.controllers",
								"com.insurance.api.services",
								"com.insurance.api.services.implement",
								"com.insurance.api.hateoas"})
@EntityScan("com/insurance/api/model/entities")

public class StockApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(StockApplication.class);

	@Autowired
	ProductRepository productRepo;
	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	MovementTypeRepository movementTypeRepo;
	@Autowired
	MovementRepository movementRepo;
	@Autowired
	ProductModelAssembler productModelAssembler;

	static void print(String toPrint) {
		System.out.println(toPrint);
	}

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {



// ----------  Category -----------------------------------
		/*Category allCategories = categoryRepo.findByid(2L);
		print(""+ allCategories);*/
/*
		Category newInsert = categoryRepo.save(new Category("Sport"));
		print(""+ newInsert);*/

//		categoryRepo.updateName("Sports",4L);

		//categoryRepo.delete(4L);
//--------------------------------------------------------------------------
// --------------- Movement type -------------------------------------------
	/*	List<MovementType> allMoveTypes = movementTypeRepo.findAll();
		logger.info("Todos los tipos  -----------------------------------------");
		allMoveTypes.forEach(System.out::println);
		logger.info("Tipo por id -------------------------------------------------");
		MovementType movementType1 = movementTypeRepo.findByid(1L);
		print(""+ movementType1);
		logger.info("Insert -------------------------------------------------------");
		MovementType insert = movementTypeRepo.save(new MovementType("Cheque"));
		print(""+ insert);*/
/*		logger.info("Update ----------------------------------------------------------");
		movementTypeRepo.updateName("Check",3L);*/
	/*	logger.info("Delete ---------------------------------------------------------------");
		movementTypeRepo.delete(3L);*/
// -----------------------------------------------------------------------------------------------

//------------------- Products -----------------------------------------------------------------

		/*List<Product> all = productRepo.findAllOrderByid();

		all.forEach(System.out::println);

		List<Product> inStock = productRepo.inStock();

		inStock.forEach(System.out::println);*/

/*		Product maxStock = productRepo.maxInStock();
		print(""+ maxStock);

		ProductAndCategory productByCategory = productRepo.productsInCategory(2L);
		print(""+ productByCategory);

		List<Product> productsOfElectric = productRepo.productsForCategory(1L);
		productsOfElectric.forEach(System.out::println);

		productRepo.changeCategory(1L, categoryRepo.findByid(2L));*/

		//productRepo.overWrite(1L,categoryRepo.findByid(1L),"Samsung","Future x-100","One",0 );

		//productRepo.deleteById(239L);

		//productRepo.deleteByBrand("Lg");

		//--------------------------------------------------------------------

// -------------- Movements ------------------------------------------

	/*	List<Movement> allMovement = movementRepo.findAllOrderBydate();
		allMovement.forEach(System.out::println);*/

/*		List<Movement> buysForYear = movementRepo.buysByYear("2020");
		buysForYear.forEach(System.out::println);
		*/

/*		List<Movement> buys = movementRepo.historicalPucharses();
		List<Movement> sales = movementRepo.historicalSales();
		buys.forEach(System.out::println);
		sales.forEach(System.out::println);
		*/

		//print(""+ movementRepo.balance());
		//movementRepo.save(new Movement(1))
		//insert into movement (id_product,id_movement_type,"date",quantity,price_unit,total) values (195,1,'2020/10/6',162,1013,-164106);

//		categoryRepo.save(new Category("Sport"));
		/*Category category = categoryRepo.getOneById(1L);
		logger.info(category + "");
		Product product = new Product(category,"Apple","Iphone 6","One",0);
		logger.info("" + product);
		productRepo.save(product);*/ //El valor de id aparece como nullo, pero si deseamos consultarlo tendra el id correspondiete segun
		// las secuencia de auto incremento


	}
}
