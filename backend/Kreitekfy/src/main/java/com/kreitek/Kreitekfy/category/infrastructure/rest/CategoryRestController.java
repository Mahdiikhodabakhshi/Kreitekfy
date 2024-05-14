package com.kreitek.Kreitekfy.category.infrastructure.rest;

import com.kreitek.Kreitekfy.category.application.dto.CategoryDto;
import com.kreitek.Kreitekfy.category.application.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryRestController {
    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/category" , produces = "application/json")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping(value = "/category-Pageable" , produces = "application/json")
    public ResponseEntity<Page<CategoryDto>> getCategoriesByCriteriaPaged(
            @RequestParam(value = "filter", required = false) String filter, Pageable pageable) {

        Page<CategoryDto> categoryDtos = categoryService.getCategoriesByCriteriaPaged(pageable, filter);
        return ResponseEntity.ok(categoryDtos);
    }

    @GetMapping(value = "/category/{categoryId}" , produces = "application/json")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId)
                .map(category -> new ResponseEntity<>(category , HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping(value = "/category" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        categoryDto =categoryService.saveCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryDto);
    }

    @PatchMapping(value = "/category" , produces = "application/json" , consumes = "application/json")
    public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto) {
        categoryDto = categoryService.saveCategory(categoryDto);
        return ResponseEntity.ok(categoryDto);
    }

    @DeleteMapping(value = "/category/{categoryId}" , produces = "application/json")
    public ResponseEntity<CategoryDto> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

}
