package tacos.entity;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

@Data
@UserDefinedType
public class TacoUDT {
    private final String name;
    private final List<IngredientUDT> ingredients;
}
