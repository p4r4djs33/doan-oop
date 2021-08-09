package com.example.doanoopkitchenmanage.service.dish;

import com.example.doanoopkitchenmanage.model.Dish;
import com.example.doanoopkitchenmanage.service.GeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DishService extends GeneralService<Dish> {
    Page<Dish> findAll(Pageable pageable);
}
