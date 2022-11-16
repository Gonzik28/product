package com.gonzik28.dto.utils;

import com.gonzik28.dto.RequestListDto;
import com.gonzik28.dto.ResponseListDto;
import com.gonzik28.dto.ResponseProductDto;
import com.gonzik28.entity.ListEntity;
import com.gonzik28.entity.ProductEntity;

import java.util.List;

public class ListUtils {
    public static ResponseListDto listEntityToDto(ListEntity listEntity) {
        ResponseListDto responseListDto = new ResponseListDto();
        responseListDto.setId(listEntity.getId());
        responseListDto.setName(listEntity.getName());
        List<ResponseProductDto> listProduct = ProductUtils.productEntityToDtos(listEntity.getProductEntityList());
        responseListDto.setRequestProductDtoList(listProduct);
        responseListDto.setKcal(listProduct.stream().map(x -> x.getKcal()).reduce((x, y) -> x + y).orElse(0));
        return responseListDto;
    }

    public static ListEntity listDtoToEntity(RequestListDto requestListDto, List<ProductEntity> productEntityList) {
        ListEntity listEntity = new ListEntity();
        listEntity.setId(requestListDto.getId());
        listEntity.setName(requestListDto.getName());
        listEntity.setProductEntityList(productEntityList);
        return listEntity;
    }
}
