package com.codewithmosh.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.codewithmosh.store.dtos.ProductDto;
import com.codewithmosh.store.dtos.UpdateUserRequest;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.entities.User;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "category.id")
    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
    // void update(ProductDto productDto, @MappingTarget Product Product); //because productDto doesnt have an id to map to product, the id in product becomes null
   
    @Mapping(target = "id", ignore = true) // so we do this instead
    void update(ProductDto productDto, @MappingTarget Product Product);

}
