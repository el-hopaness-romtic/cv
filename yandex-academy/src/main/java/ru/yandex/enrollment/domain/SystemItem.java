package ru.yandex.enrollment.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.yandex.enrollment.enums.SystemItemType;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "system_item")
public class SystemItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonIgnore
    private Long id;

    @Column(name = "item_id", nullable = false, unique = true)
    @JsonProperty("id")
    private String itemId;

    @Column(name = "url")
    private String url;

    @Column(name = "date", nullable = false)
    private ZonedDateTime date;

    public ZonedDateTime getDate() {
        if (type == SystemItemType.FILE)
            return date;

        return Stream.concat(
                        children.stream().map(SystemItem::getDate),
                        Stream.of(date)
                )
                .max(Comparator.naturalOrder())
                .get();
    }

    @Column(name = "parent_id")
    @JsonIgnore
    private Long parentId;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private SystemItemType type;

    @Column(name = "size")
    private Long size;

    public Long getSize() {
        if (type == SystemItemType.FILE) return size;
        return children.stream().mapToLong(SystemItem::getSize).sum();
    }

    @Transient
    @JsonProperty("parentId")
    private String parentItemId;

    @Transient
    private final List<SystemItem> children = new LinkedList<>();

    public List<SystemItem> getChildren() {
        if (type == SystemItemType.FILE) return null;
        children.forEach(child -> child.setParentItemId(getItemId()));
        return children;
    }
}
