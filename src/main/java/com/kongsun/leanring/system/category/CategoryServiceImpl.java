package com.kongsun.leanring.system.category;

import com.kongsun.leanring.system.common.PageDTO;
import com.kongsun.leanring.system.common.PageUtil;
import com.kongsun.leanring.system.common.PaginationUtil;
import com.kongsun.leanring.system.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "categoriesCache")
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    @CachePut(key = "#result.id")
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    @Cacheable(key = "#id")
    public Category getById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Category", id));
    }

    @Override
    @CachePut(key = "#id")
    public Category update(Long id, Category category) {
        Category byId = getById(id);
        byId.setName(category.getName());
        return categoryRepository.save(byId);
    }

    @Override
    @CacheEvict(key = "#id")
    public void deleteById(Long id) {
        getById(id);
        categoryRepository.deleteById(id);
    }

    @Override
    @Cacheable
    public PageDTO getAll(Map<String, String> params) {
        Specification<Category> spec = Specification.where(null);
        if(params.containsKey("name")){
            spec = CategorySpec.containName(params.get("name"));
        }
        Pageable pageable = PaginationUtil.getPageNumberAndPageSize(params);
        return new PageDTO(categoryRepository.findAll(spec, pageable));
    }
}
