package ru.koleson.photousersapi.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.koleson.photousersapi.model.AccountRest;

import java.util.ArrayList;
import java.util.List;

@FeignClient(name = "accmanag")
public interface ServiceAccountClient {

    @GetMapping("/account")
    @Retry(name = "accmanag")
    @CircuitBreaker(name = "accmanag", fallbackMethod = "getAlbumsFallback")
    public List<AccountRest> getAllAccountsUser();

    default List<AccountRest> getAlbumsFallback( Throwable exception) {
        System.out.println("Exception is " + exception.getLocalizedMessage() + System.lineSeparator());
        return new ArrayList<AccountRest>();
    }
}
