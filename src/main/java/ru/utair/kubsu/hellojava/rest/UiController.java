package ru.utair.kubsu.hellojava.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //аннотация bin, rest
@RequestMapping(path="rest/test",produces="application/json") //produces - методы будут отдавать json
public class UiController {

    @GetMapping
    public String text1() {
        return "kek";
    }
}
