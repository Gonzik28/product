package com.gonzik28.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Запрашиваемые параметры продукта")
public class RequestProductDto {
    @Schema(description = "Идентификатор продукта")
    private long id;
    @Schema(description = "Название продукта")
    private String name;
    @Schema(description = "Описание продукта")
    private String description;
    @Schema(description = "Количество каллорий в продукте")
    private int kcal;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

}
