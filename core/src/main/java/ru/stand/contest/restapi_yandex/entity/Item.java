package ru.stand.contest.restapi_yandex.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import ru.stand.contest.restapi_yandex.enums.Type;
import ru.stand.contest.restapi_yandex.model.SystemItemType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "item")
@Getter
@Setter
@ToString
public class Item {

    @Id
    private UUID id;

    @Column(name = "url")
    private String url;

    @Column(name = "date")
    private Date date;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private SystemItemType type;

    @Column(name = "parent_id")
    private UUID parentId;

    @Column(name = "size")
    private Long size;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @ToString.Exclude
    private List<Item> items;

    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Item item = (Item) o;
        return id != null && Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
