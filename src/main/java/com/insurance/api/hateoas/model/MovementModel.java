package com.insurance.api.hateoas.model;

import com.insurance.api.model.entities.MovementType;
import com.insurance.api.model.entities.Product;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;
import java.util.List;

public class MovementModel extends RepresentationModel<MovementModel> {

    private Long id;
  //  private MovementType movementType;
    private Long movementTypeId;
    //private String movemetTypeName;
//    private Product product;
    private int productId;
    private int quantity;
    private int priceUnit;
    private Date date;
    private int total;

    public MovementModel(Long id, Long movementTypeId, int productId, int quantity, int priceUnit, Date date, int total) {
        this.id = id;
        this.movementTypeId = movementTypeId;
        this.productId = productId;
        this.quantity = quantity;
        this.priceUnit = priceUnit;
        this.date = date;
        this.total = total;
    }

    public MovementModel(Link initialLink, Long id, Long movementTypeId, int productId, int quantity, int priceUnit, Date date, int total) {
        super(initialLink);
        this.id = id;
        this.movementTypeId = movementTypeId;
        this.productId = productId;
        this.quantity = quantity;
        this.priceUnit = priceUnit;
        this.date = date;
        this.total = total;
    }

    public MovementModel(List<Link> initialLinks, Long id, Long movementTypeId, int productId, int quantity, int priceUnit, Date date, int total) {
        super(initialLinks);
        this.id = id;
        this.movementTypeId = movementTypeId;
        this.productId = productId;
        this.quantity = quantity;
        this.priceUnit = priceUnit;
        this.date = date;
        this.total = total;
    }

    public MovementModel(){super();}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

  /*  public MovementType getMovementType() {
        return movementType;
    }*/

  /*  public void setMovementType(MovementType movementType) {
        this.movementType = movementType;
    }*/

    public Long getMovementTypeId() {
        return movementTypeId;
    }

    public void setMovementTypeId(Long movementTypeId) {
        this.movementTypeId = movementTypeId;
    }

 /*   public String getMovemetTypeName() {
        return movemetTypeName;
    }*/

/*    public void setMovemetTypeName(String movemetTypeName) {
        this.movemetTypeName = movemetTypeName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }*/

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(int priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "MovementModel{" +
                "id=" + id +
                ", movementTypeId=" + movementTypeId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", priceUnit=" + priceUnit +
                ", date=" + date +
                ", total=" + total +
                '}';
    }
}
