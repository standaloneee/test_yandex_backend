package ru.stand.contest.restapi_yandex.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;
import ru.stand.contest.restapi_yandex.model.SystemItem;
import ru.stand.contest.restapi_yandex.service.ItemService;

import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-09-09T13:45:12.975114900+03:00[Europe/Moscow]")
@Controller
@RequestMapping("${openapi.yetAnotherDiskOpen.base-path:}")
@RequiredArgsConstructor
public class NodesApiController implements NodesApi {

    private final NativeWebRequest request;
    private final ItemService itemService;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<SystemItem> getSystemItem(String id) {
        return itemService.getSystemItem(id);
    }

}
