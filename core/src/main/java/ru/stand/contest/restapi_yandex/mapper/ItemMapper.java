package ru.stand.contest.restapi_yandex.mapper;

import org.mapstruct.Context;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.openapitools.jackson.nullable.JsonNullable;
import ru.stand.contest.restapi_yandex.dto.SystemItemImportDto;
import ru.stand.contest.restapi_yandex.entity.Item;
import ru.stand.contest.restapi_yandex.model.SystemItem;
import ru.stand.contest.restapi_yandex.model.SystemItemType;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.FIELD,
        imports = {Date.class, JsonNullable.class, java.util.Date.class}
)
public abstract class ItemMapper {

    // TODO ЗАДАВАТЬ РАЗМЕР ВО ВРЕМЯ ЗАПИСИ В ТАБЛИЦУ!!!!!

    public static final ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

//    @Mapping(target = "items", source = "")
    @Mapping(target = "date", source = "date")
    @Mapping(target = "url", expression = "java(systemItem.getUrl())")
    @Mapping(target = "size", expression = "java(systemItem.getSize())")
    public abstract Item toEntity(SystemItemImportDto systemItem, Date date);

    @Mapping(target = "children", source = "item", qualifiedByName = "children")
    @Mapping(target = "size", expression = "java(item.getSize())")
    @Mapping(target = "url", expression = "java(JsonNullable.of(item.getUrl()))")
    @Mapping(target = "date", expression = "java(new java.util.Date(item.getDate().getTime()))")
    public abstract SystemItem toDto(Item item);

    @Named("children")
    public List<SystemItem> children(Item item) {
        if (SystemItemType.FILE.equals(item.getType())) {
            return null;
        }
        if (!item.getItems().isEmpty()) {
            return item.getItems().stream().map(this::toDto).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


}
