// package com.example.springbreaking.restHateoas;
//
// import org.springframework.http.HttpEntity;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;
//
// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
// import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//
// @RestController
// public class GreetingRestController {
//
//     private static final String TEMPLATE = "Hello, %s!";
//
//     @RequestMapping("/greeting")
//     public HttpEntity<?> greeting(
//             @RequestParam(value = "name", defaultValue = "World") String name) {
//         Greeting greeting = new Greeting(String.format(TEMPLATE, name));
//         System.out.println(greeting);
//
//         // 메서드에 인수를 전달하여 특정 컨트롤러 메서드에 대한 링크를 생성
//         greeting.add(linkTo(methodOn(GreetingRestController.class).greeting(name)).withSelfRel());
//
//         return new ResponseEntity<>(greeting, HttpStatus.OK);
//     }
//
// }