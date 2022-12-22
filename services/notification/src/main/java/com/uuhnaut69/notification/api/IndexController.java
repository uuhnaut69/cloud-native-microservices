package com.uuhnaut69.notification.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class IndexController {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("Hi");
    }

}
