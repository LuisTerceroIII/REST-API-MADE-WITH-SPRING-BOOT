package com.insurance.api.model.entities;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="MovementType",schema = "PUBLIC")
public class MovementType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    Long id;
    @Column(name="name")
    String name;

    @OneToMany(mappedBy = "id_movement_type", cascade = CascadeType.ALL)
    private Set <Movement> movements = new HashSet<>();

    public MovementType() {
    }

    public MovementType(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MovementType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
