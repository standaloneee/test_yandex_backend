package ru.stand.contest.restapi_yandex.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.stand.contest.restapi_yandex.entity.Item;
import ru.stand.contest.restapi_yandex.handler.ValidationItemException;
import ru.stand.contest.restapi_yandex.model.SystemItemType;

import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j
public class ImportsValidator {
    Set<Item> itemSet = new HashSet<>();
    boolean firstElem = false;

    public void validateItem(Item systemItem) {

        if (SystemItemType.FOLDER.equals(systemItem.getType())) {
            if (systemItem.getUrl() != null) {
                throw new ValidationItemException("Validation Failed");
            }
            if (systemItem.getSize() != null) {
                throw new ValidationItemException("Validation Failed");
            }
            else {
                systemItem.setSize(0L);
            }
        } else {
            if (systemItem.getUrl().length() > 255) {
                throw new ValidationItemException("Validation Failed");
            }
            if (systemItem.getSize() <= 0) {
                throw new ValidationItemException("Validation Failed");
            }
        }
        if (!firstElem) {
            itemSet.add(systemItem);
            firstElem = true;
        } else {
            for (var item :
                    itemSet) {
                if (item.getId().equals(systemItem.getId())) {
                    throw new ValidationItemException("not unique id");
                }

                if (systemItem.getParentId() == null) {
                } else if (systemItem.getParentId().equals(item.getId()) && item.getType().equals(SystemItemType.FILE)) {
                    throw new ValidationItemException("file can't have parent as file");
                }
            }
            itemSet.add(systemItem);
        }


    }



}
