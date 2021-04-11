package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.implementations;

import lombok.AllArgsConstructor;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.yfsmsystem.distributedsystems.productcentral.core.category.Category;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryAlreadyExistException;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.exception.CategoryNotFound;
import br.com.yfsmsystem.distributedsystems.productcentral.core.category.port.CategoryRepositoryService;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.converters.CategoryConverter;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.entities.CategoryEntity;
import br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.persistence.repositories.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryRepositoryServiceImplementation implements CategoryRepositoryService {

  private final CategoryRepository categoryRepository;

  @Override
  public Category saveCategory(Category category) {
    if (!categoryRepository.existsByDescription(category.getDescription())) {
      category.setActive(!category.isActive());
      CategoryEntity categorySaved = categoryRepository
          .save(CategoryConverter.mapObjectToEntity(category));
      return CategoryConverter.mapEntitytoObject(categorySaved);
    } else {
      throw new CategoryAlreadyExistException();
    }
  }

  @Override
  public Category getCategoryById(Long id) {
    return CategoryConverter.mapEntitytoObject(
        categoryRepository.findById(id).orElseThrow(CategoryNotFound::new));
  }

  @Override
  public Category updateCategory(Long id, Category category) {
    if (categoryRepository.existsById(id)) {
      CategoryEntity categorySaved = categoryRepository
          .save(CategoryConverter.mapObjectToEntity(category));
      return CategoryConverter.mapEntitytoObject(categorySaved);
    } else {
      throw new CategoryNotFound();
    }
  }

  @Override
  public void deleteCategoryById(Long id) {
    if (categoryRepository.existsById(id)) {
      categoryRepository.deleteById(id);
    } else {
      throw new CategoryNotFound();
    }
  }

  @Override
  @Cacheable("findAllCategory")
  public List<Category> getAllCategory() {
    return categoryRepository.findAll().stream().map(CategoryConverter::mapEntitytoObject)
        .collect(Collectors.toList());
  }

  @Override
  public List<Category> getCategoryByActiveStatus(Boolean status) {
    return categoryRepository.findAllByActive(status).stream()
        .map(CategoryConverter::mapEntitytoObject)
        .collect(Collectors.toList());
  }

  @Override
  public Boolean verifyCategoryById(Long id) {
    return this.getAllCategory().stream()
        .anyMatch(x -> x.getId().equals(id));
  }
}
