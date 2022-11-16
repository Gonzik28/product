package com.gonzik28.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = ListEntity.TABLE)
public class ListEntity {
    public static final String TABLE = "lists";
    @Id
    private long id;
    private String name;
    @ManyToMany
    @JoinTable(
            name = "lists_products",
            joinColumns = @JoinColumn(name = "id_list"),
            inverseJoinColumns = @JoinColumn(name = "id_product"))
    private List<ProductEntity> products = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getProductEntityList() {
        return products;
    }

    public void setProductEntityList(List<ProductEntity> products) {
        this.products = products;
    }

}
