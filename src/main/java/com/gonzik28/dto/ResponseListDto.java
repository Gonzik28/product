package com.gonzik28.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Параметры списка продуктов, указанные в базе данных")
public class ResponseListDto {
    @Schema(description = "Идентификатор списка продуктов")
    private long id;
    @Schema(description = "Название списка продуктов")
    private String name;
    @Schema(description = "Перечень продуктов, входящих в список, и их характеристика")
    private List<ResponseProductDto> responseProductDtoList;

    @Schema(description = "Колличество каллорий в списке")
    private long kcal;

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

    public List<ResponseProductDto> getRequestProductDtoList() {
        return responseProductDtoList;
    }

    public void setRequestProductDtoList(List<ResponseProductDto> responseProductDtoList) {
        this.responseProductDtoList = responseProductDtoList;
    }

    public long getKcal() {
        return kcal;
    }

    public void setKcal(long kcal) {
        this.kcal = kcal;
    }
}
