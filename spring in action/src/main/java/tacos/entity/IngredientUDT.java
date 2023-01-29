package tacos.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@Data
@UserDefinedType
public class IngredientUDT {

    private final String name;
    private final Ingredient.Type type;

}
