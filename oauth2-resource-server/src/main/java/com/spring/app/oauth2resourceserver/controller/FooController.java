package com.spring.app.oauth2resourceserver.controller;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

import com.spring.app.oauth2resourceserver.entity.Foo;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class FooController {

    public FooController(){
        super();
    }

    //API - FOO - READ
    @PreAuthorize("#oauth2.hasScope('foo') and #oauth2.hasScope('read')")
    @GetMapping(value = "/foos/{fooId}")
    @ResponseBody
    public Foo findById(@PathVariable final long fooId){
        return new Foo(Long.parseLong(randomNumeric(2)), randomAlphabetic(4));
    }

    //API - FOO - WRITE
    @PreAuthorize("#oauth2.hasScope('foo') and #oauth2.hasScope('write')")
    @PostMapping(value = "/foos")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Foo createFoo(@RequestBody final Foo foo){
        foo.setId(Long.parseLong(randomNumeric(2)));
        return foo;
    }
}
