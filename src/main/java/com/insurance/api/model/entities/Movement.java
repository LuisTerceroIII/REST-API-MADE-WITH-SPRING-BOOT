package com.insurance.api.model.entities;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="Movement",schema = "PUBLIC")
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_movement_type")
    private MovementType id_movement_type;

    @Column(name="id_product")
    private int id_product;

    @Column(name="quantity")
    private int quantity;

    @Column(name = "price_unit")
    private int price_unit;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private  Date date;

    @Column(name = "total")
    private  int total;

    public Movement() {
    }

    public Movement(MovementType id_movement_type, int id_product, int quantity, int price_unit, Date date, int total) {
        this.id_movement_type = id_movement_type;
        this.id_product = id_product;
        this.quantity = quantity;
        this.price_unit = price_unit;
        this.date = date;
        this.total = total;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovementType getId_movement_type() {
        return id_movement_type;
    }

    public void setId_movement_type(MovementType id_movement_type) {
        this.id_movement_type = id_movement_type;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice_unit() {
        return price_unit;
    }

    public void setPrice_unit(int price_unit) {
        this.price_unit = price_unit;
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
        return "Movement{" +
                "id=" + id +
                ", id_movement_type=" + id_movement_type +
                ", id_product=" + id_product +
                ", quantity=" + quantity +
                ", price_unit=" + price_unit +
                ", date=" + date +
                ", total=" + total +
                '}';
    }
}
