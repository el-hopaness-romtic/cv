package tacos.web;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import tacos.data.IngredientRepository;
import tacos.entity.Ingredient;
import tacos.entity.IngredientUDT;

@Component
@RequiredArgsConstructor
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {
    private final IngredientRepository ingredientRepo;

    @Override
    public IngredientUDT convert(String id) {
        Ingredient ingredient = ingredientRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("No Ingredient with id = " + id));
        return new IngredientUDT(ingredient.getName(), ingredient.getType());
    }
}
