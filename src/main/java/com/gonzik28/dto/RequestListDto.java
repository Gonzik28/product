package com.gonzik28.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Запрашиваемые параметры списка продуктов")
public class RequestListDto {
    @Schema(description = "Идентификатор списка продуктов")
    private long id;
    @Schema(description = "Название списка продуктов")
    private String name;
    @Schema(description = "Перечень идентификаторов продуктов в списке")
    private List<Long> productIdList;

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

    public List<Long> getProductIdList() {
        return productIdList;
    }

    public void setProductIdList(List<Long> productIdList) {
        this.productIdList = productIdList;
    }
}
