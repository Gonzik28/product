package com.gonzik28.controller;

import com.gonzik28.dto.RequestListDto;
import com.gonzik28.dto.ResponseListDto;
import com.gonzik28.service.ListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/lists/")
@Tag(name = "Список продуктов", description = "Создан для работы со списком продуктов.")
public class ListController {

    @Autowired
    private ListService listService;

    @GetMapping(value = "{id}")
    @Operation(summary = "Получение списка продуктов по Id")
    public ResponseEntity<ResponseListDto> findById(@PathVariable
                                                    @Parameter(description = "Идентификатор списка продуктов")
                                                    long id) throws NoSuchElementException {
        try {
            ResponseListDto listDto = listService.findById(id);
            return ResponseEntity.ok(listDto);
        } catch (NoSuchElementException elementException) {
            System.out.println(elementException.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "create")
    @Operation(summary = "Создание списка товаров")
    public ResponseEntity<ResponseListDto> create(@RequestBody
                                                  @Parameter(description = "Новый список продуктов")
                                                  RequestListDto requestListDto) {
        ResponseListDto listDto = listService.create(requestListDto);
        return ResponseEntity.ok(listDto);
    }

    @PostMapping(value = "update")
    @Operation(summary = "Изменение списка товаров по Id")
    public ResponseEntity<ResponseListDto> update(@RequestBody
                                                  @Parameter(description = "Измененный список продуктов")
                                                  RequestListDto requestListDto) throws NoSuchElementException {
        try {
            ResponseListDto listDto = listService.update(requestListDto);
            return ResponseEntity.ok(listDto);
        } catch (NoSuchElementException elementException) {
            System.out.println(elementException.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "add/{listId}&{productId}")
    @Operation(summary = "Добавление товара по Id в список")
    public ResponseEntity<ResponseListDto> addProduct(@PathVariable
                                                      @Parameter(description = "Идентификатор списка продуктов")
                                                      long listId,
                                                      @PathVariable
                                                      @Parameter(description = "Идентификатор продукта")
                                                      long productId) throws NoSuchElementException {
        try {
            ResponseListDto listDto = listService.add(listId, productId);
            return ResponseEntity.ok(listDto);
        } catch (NoSuchElementException elementException) {
            System.out.println(elementException.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping(value = "{id}")
    @Operation(summary = "Удаление списка продуктов по Id")
    public ResponseEntity<Void> delete(@PathVariable
                                       @Parameter(description = "Идентификатор списка продуктов")
                                       long id) {
        listService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
