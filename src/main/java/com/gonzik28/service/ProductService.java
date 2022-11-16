package com.gonzik28.service;

import com.gonzik28.dto.RequestProductDto;
import com.gonzik28.dto.ResponseProductDto;
import com.gonzik28.dto.utils.ProductUtils;
import com.gonzik28.entity.ProductEntity;
import com.gonzik28.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@Transactional
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ResponseProductDto findById(long id) {
        ProductEntity productEntity = productRepository.findById(id).get();
        return ProductUtils.productEntityToDto(productEntity);
    }


    public ResponseProductDto create(RequestProductDto productDto) {
        ProductEntity productEntity = ProductUtils.productDtoToEntity(productDto);
        productEntity = productRepository.save(productEntity);
        return ProductUtils.productEntityToDto(productEntity);
    }

    public ResponseProductDto update(RequestProductDto productDto) {
        if (!productRepository.findById(productDto.getId()).isPresent()) {
            throw new NoSuchElementException("Продукта с указанным id = "
                    + productDto.getId() + " не существует");
        } else {
            ProductEntity productEntity = productRepository.findById(productDto.getId()).get();
            productEntity.setId(productDto.getId());
            productEntity.setName(productDto.getName());
            productEntity.setDescription(productDto.getDescription());
            productEntity.setKcal(productDto.getKcal());
            productEntity = productRepository.save(productEntity);
            return ProductUtils.productEntityToDto(productEntity);
        }
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

}
