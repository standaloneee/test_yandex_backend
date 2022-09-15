package ru.stand.contest.restapi_yandex.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import ru.stand.contest.restapi_yandex.dto.SystemItemImportRequest;
import ru.stand.contest.restapi_yandex.service.ItemService;

import javax.annotation.Generated;
import java.util.List;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-09T13:45:12.975114900+03:00[Europe/Moscow]")
@Controller
@RequestMapping("${openapi.yetAnotherDiskOpen.base-path:}")
@RequiredArgsConstructor
public class ImportsApiController implements ImportsApi {

    private final NativeWebRequest request;
    private final ItemService itemService;



    @Override
    public void save(List<SystemItemImportRequest> systemItemImportRequest) {
        itemService.setItems(systemItemImportRequest);
    }

}
