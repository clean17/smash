//package com.example.springbreaking.messagingGcpPubSub;
//
//import lombok.RequiredArgsConstructor;
//import com.example.springbreaking.messagingGcpPubSub.PubSubApplication.PubsubOutboundGateway;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.view.RedirectView;
//
///**
// * 사전작업으로 구글 GCP를 먼저 공부해야할듯
// * https://www.daddyprogrammer.org/post/13998/spring-cloud-gcp-starter-pubsub/
// * https://spring.io/guides/gs/messaging-gcp-pubsub/
// *
// * 필요한것
// * A Google Cloud Platform project with billing and Pub/Sub enabled
// * https://cloud.google.com/pubsub/docs/publish-receive-messages-console?hl=ko
// *
// * Google Cloud SDK
// * https://cloud.google.com/sdk?hl=ko
// *
// * 아마 파이어베이스처럼 구글의 서비스를 이용한것 같다.
// */
//@Controller
//@RequiredArgsConstructor
//public class WebAppController {
//    private PubsubOutboundGateway messagingGateway;
//
//    @GetMapping(value = "/pub")
//    public String getPub() {
//        return "pub";
//    }
//
//
//    @PostMapping("/publishMessage")
//    @ResponseBody
//    public RedirectView publishMessage(@RequestParam("message") String message) {
//        messagingGateway.sendToPubsub(message);
//        return new RedirectView("/");
//    }
//}