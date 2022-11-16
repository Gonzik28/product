package com.gonzik28.dto.utils;

import com.gonzik28.dto.RequestProductDto;
import com.gonzik28.dto.ResponseProductDto;
import com.gonzik28.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProductUtils {

    public static ResponseProductDto productEntityToDto(ProductEntity productEntity) {
        ResponseProductDto productDto = new ResponseProductDto();
        productDto.setId(productEntity.getId());
        productDto.setName(productEntity.getName());
        productDto.setDescription(productEntity.getDescription());
        productDto.setKcal(productEntity.getKcal());
        return productDto;
    }

    public static List<ResponseProductDto> productEntityToDtos(List<ProductEntity> productEntities) {
        return productEntities
                .stream()
                .map(ProductUtils::productEntityToDto)
                .collect(Collectors.toList());
    }

    public static ProductEntity productDtoToEntity(RequestProductDto requestProductDto) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(requestProductDto.getId());
        productEntity.setName(requestProductDto.getName());
        productEntity.setDescription(requestProductDto.getDescription());
        productEntity.setKcal(requestProductDto.getKcal());
        return productEntity;
    }

}
