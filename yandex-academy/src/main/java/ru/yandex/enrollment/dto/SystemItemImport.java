package ru.yandex.enrollment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import ru.yandex.enrollment.enums.SystemItemType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SystemItemImport {
    @NotNull
    @JsonProperty("id")
    private String itemId;

    @JsonProperty
    @Length(max = 255)
    private String url;

    @JsonProperty("parentId")
    private String parentItemId;

    @NotNull
    @JsonProperty
    private SystemItemType type;

    @JsonProperty
    @Min(0)
    private Long size;
}
