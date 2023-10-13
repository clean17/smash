//package com.example.springbreaking.accessingDataJpa;
//
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class FundamentalController {
//
////    @GetMapping(value = "/")
////    public ResponseEntity<String> customRoot() {
////        return ResponseEntity.ok("Custom Root Response And is not index2.html");
////    }
//
//
////    @GetMapping("/")
////    public ResponseEntity<Map<String, Object>> customRoot() {
////        Map<String, Object> response = new HashMap<>();
////        Map<String, Object> links = new HashMap<>();
////        Map<String, Object> peopleLink = new HashMap<>();
////
////        peopleLink.put("href", "http://localhost:8080/people{?page,size,sort}");
////        peopleLink.put("templated", true);
////        links.put("people", peopleLink);
////        response.put("_links", links);
////
////        return ResponseEntity.ok(response);
////    }
//}
