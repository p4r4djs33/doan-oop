package com.example.doanoopkitchenmanage.service.mainIngredient;

import com.example.doanoopkitchenmanage.model.MainIngredient;
import com.example.doanoopkitchenmanage.service.GeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MainIngredientService extends GeneralService<MainIngredient> {
    Page<MainIngredient> findAll(Pageable pageable);
}
