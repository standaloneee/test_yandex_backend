package ru.stand.contest.restapi_yandex.service;

import liquibase.repackaged.net.sf.jsqlparser.expression.operators.relational.ItemsList;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stand.contest.restapi_yandex.dto.SystemItemImportDto;
import ru.stand.contest.restapi_yandex.dto.SystemItemImportRequest;
import ru.stand.contest.restapi_yandex.entity.Item;
import ru.stand.contest.restapi_yandex.handler.ItemNotFoundException;
import ru.stand.contest.restapi_yandex.mapper.ItemMapper;
import ru.stand.contest.restapi_yandex.model.SystemItem;
import ru.stand.contest.restapi_yandex.repository.ItemRepository;
import ru.stand.contest.restapi_yandex.validator.ImportsValidator;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;

    @SneakyThrows
    @Transactional()
    public SystemItemImportDto setItems(List<SystemItemImportRequest> systemItem) throws Error {
        ImportsValidator checkValidItem = new ImportsValidator();
//        log.debug("setItemsService");
        List<Item> itemsList = new ArrayList<>();
//        List<Item> FiolderPlusSizeList = new ArrayList<>();
        for (SystemItemImportRequest request : systemItem) {
            itemsList.addAll(request.getItems().stream()
                    .map(item -> ItemMapper.INSTANCE.toEntity(item, new Date(request.getUpdateDate().getTime())))
                    .peek(checkValidItem::validateItem)


                    .collect(Collectors.toList()));
        }


        itemRepository.saveAll(itemsList);
        log.debug(String.valueOf(itemsList));



        log.debug(String.valueOf(itemsList));

        for (Item item:itemsList) {
            updateFolderSize(item);
        }
        for (var item : itemsList) {
           var parent = item;
           var child = item;
            while(child.getParentId()!=null){
                parent = itemRepository.getItemById(child.getParentId());
                parent.setSize(item.getSize() + parent.getSize());
                child = parent;
            }
        }
        return null;
    }

    public void updateFolderSize(Item item) {
        var tempFolder = item;

        if (item.getParentId() != null) {
            tempFolder = itemRepository.findById(item.getParentId()).orElseThrow(() -> new ItemNotFoundException("хуй"));
        } else {
            return;
        }
        var fSize = 0;
        for (var fItem : tempFolder.getItems()) {
            fSize += fItem.getSize();
            if (fSize == tempFolder.getSize()) {

            } else {
                tempFolder.setSize((long) fSize);
                updateFolderSize(tempFolder);
            }
        }
    }
//    public void ChangeParentFolderSize(long deltaSize, Item folder){
//        var parentFolder = folder;
//        while(folder.getParentId() != null)
//        {
//            parentFolder = itemRepository.getItemById(folder.getParentId());
//            parentFolder.setSize(parentFolder.getSize()+deltaSize);
//        }
//    }

    public ResponseEntity<SystemItem> getSystemItem(String id) {
        Optional<Item> optionalItem = itemRepository.findById(UUID.fromString(id));
        return optionalItem
                .map(item -> ResponseEntity.ok(ItemMapper.INSTANCE.toDto(item)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


    @Transactional
    public ResponseEntity<Void> deleteById(String id, java.util.Date date) {
        Optional<Item> optionalItem = itemRepository.findById(UUID.fromString(id));
        if (optionalItem.isPresent()) {
            itemRepository.deleteById(UUID.fromString(id));
            return ResponseEntity.ok().build();
        }
        throw new ItemNotFoundException("Элемент не найден.");
    }

}
