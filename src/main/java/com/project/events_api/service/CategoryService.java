package com.project.events_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.events_api.dto.CategoryDTO;
import com.project.events_api.mapper.CategoryMapper;
import com.project.events_api.model.Category;
import com.project.events_api.repository.CategoryRepository;
import com.project.events_api.util.ResourceNotFoundException;

/**
 * Classe de serviço para a entidade Category
 * CRUD de categorias
 * 
 * @author Gabrielly Freire
 * @since 1.0
 */
@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    /**
     * Cria uma nova categoria
     * @param categoryDTO - DTO da categoria
     * @return DTO da categoria criada
     */
    public CategoryDTO create(CategoryDTO categoryDTO) {
        Category category = categoryMapper.toModel(categoryDTO);
        category = categoryRepository.save(category);
        return categoryMapper.toDTO(category);
    }

    /** 
     * Retorna todas as categorias
     * @return Lista de DTOs de categorias
     */
    public List<CategoryDTO> findAll() {
        return categoryRepository.findAll().stream()
            .map(categoryMapper::toDTO)
            .toList();
    }

    /**
     * Retorna uma categoria por ID
     * @param id - ID da categoria
     * @return DTO da categoria
     */
    public CategoryDTO findById(Long id) {
        Category category = validateExistsCategory(id);

        return categoryMapper.toDTO(category);
    }

    /**
     * Atualiza uma categoria existente
     * @param id - ID da categoria a ser atualizada
     * @param categoryDTO - DTO com os novos dados da categoria
     * @return DTO atualizado da categoria
     */
    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        Category oldCategory = validateExistsCategory(id);
        Category newCategory = categoryMapper.toModel(categoryDTO);

        oldCategory = helperUpdate(oldCategory, newCategory);

        return categoryMapper.toDTO(categoryRepository.save(oldCategory));
    }

    /**
     * Deleta uma categoria por ID
     * @param id - ID da categoria a ser deletada
     */
    public void delete(Long id) {
        validateExistsCategory(id);
        categoryRepository.deleteById(id);
    }

    /**
     * Valida se a categoria existe pelo id
     * @param id - ID da categoria a ser verificada
     * @return Categoria encontrada
     */
    private Category validateExistsCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Categoria não encontrada com o ID: " + id)
        );
    }

    /**
     * Atualiza os dados da categoria
     * @param oldCategory - Categoria antiga
     * @param newCategory - Categoria nova
     * @return Categoria atualizada
     */
    private Category helperUpdate(Category oldCategory, Category newCategory) {
        oldCategory.setName(newCategory.getName());
        oldCategory.setDescription(newCategory.getDescription());
        return oldCategory;
    }
}
