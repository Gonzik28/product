package com.gonzik28.service;

import com.gonzik28.dto.RequestListDto;
import com.gonzik28.dto.ResponseListDto;
import com.gonzik28.dto.utils.ListUtils;
import com.gonzik28.entity.ListEntity;
import com.gonzik28.entity.ProductEntity;
import com.gonzik28.repository.ListRepository;
import com.gonzik28.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Transactional
public class ListService {
    @Autowired
    private ListRepository listRepository;

    @Autowired
    private ProductRepository productRepository;

    public ResponseListDto findById(long id) {
        ListEntity listEntity = listRepository.findById(id).get();
        return ListUtils.listEntityToDto(listEntity);
    }

    public ResponseListDto create(RequestListDto listDto) {
        List<Long> productId = listDto.getProductIdList();
        List<ProductEntity> productEntities = productId.stream()
                .map(x -> productRepository.findById(x).get())
                .collect(Collectors.toList());
        ListEntity listEntity = ListUtils.listDtoToEntity(listDto, productEntities);
        listEntity = listRepository.save(listEntity);
        return ListUtils.listEntityToDto(listEntity);
    }

    public ResponseListDto update(RequestListDto listDto) {
        if (!listRepository.findById(listDto.getId()).isPresent()) {
            throw new NoSuchElementException("Листа покупок с указанным id = " + listDto.getId() + " не существует");
        } else {
            ListEntity listEntity = listRepository.findById(listDto.getId()).get();
            listEntity.setId(listDto.getId());
            listEntity.setName(listDto.getName());
            listEntity = listRepository.save(listEntity);
            return ListUtils.listEntityToDto(listEntity);
        }
    }

    public ResponseListDto add(Long listId, Long productDtoId) {
        if (!listRepository.findById(listId).isPresent()) {
            throw new NoSuchElementException("Листа покупок с указанным id = " + listId + " не существует");
        }
        if (!productRepository.findById(productDtoId).isPresent()) {
            throw new NoSuchElementException("Продукта с указанным id = " + productDtoId + " не существует");
        } else {
            ListEntity listEntity = listRepository.findById(listId).get();
            List<ProductEntity> productEntities = listEntity.getProductEntityList();
            ProductEntity productEntity = productRepository.findById(productDtoId).get();
            productEntities.add(productEntity);
            listEntity.setProductEntityList(productEntities);
            listEntity = listRepository.save(listEntity);
            return ListUtils.listEntityToDto(listEntity);
        }
    }

    public void delete(long id) {
        listRepository.deleteById(id);
    }

}
