package com.gonzik28.controller;

import com.gonzik28.dto.RequestProductDto;
import com.gonzik28.dto.ResponseProductDto;
import com.gonzik28.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/product/")
@Tag(name = "Продукт", description = "Создан для работы с товарами.")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "{id}")
    @Operation(summary = "Получение товара по Id")
    public ResponseEntity<ResponseProductDto> findById(@PathVariable
                                                       @Parameter(description = "Идентификатор продукта")
                                                       long id) throws NoSuchElementException {
        try {
            ResponseProductDto productDto = productService.findById(id);
            return ResponseEntity.ok(productDto);
        } catch (NoSuchElementException elementException) {
            System.out.println(elementException.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "create")
    @Operation(summary = "Создание товара")
    public ResponseEntity<ResponseProductDto> create(@RequestBody
                                                     @Parameter(description = "Новый продукт")
                                                     RequestProductDto requestProductDto) {
        ResponseProductDto productDto = productService.create(requestProductDto);
        return ResponseEntity.ok(productDto);
    }

    @PostMapping(value = "update")
    @Operation(summary = "Изменение товара, по Id")
    public ResponseEntity<ResponseProductDto> update(@RequestBody
                                                     @Parameter(description = "Измененный продукт")
                                                     RequestProductDto requestProductDto) throws NoSuchElementException {
        try {
            ResponseProductDto productDto = productService.update(requestProductDto);
            return ResponseEntity.ok(productDto);
        } catch (NoSuchElementException elementException) {
            System.out.println(elementException.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "{id}")
    @Operation(summary = "Удаление товара по Id")
    public ResponseEntity<Void> delete(@PathVariable
                                       @Parameter(description = "Идентификатор продукта")
                                       long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
