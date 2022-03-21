package ru.koleson.photousersapi.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.koleson.photoappaccmanag.model.AccountRest;

import java.util.List;

@FeignClient(name = "accmanag")
public interface ServiceAccountClient {

    @GetMapping("/account")
    public List<AccountRest> getAllAccountsUser();

}
