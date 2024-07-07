package com.example.websample.controller;

import com.example.websample.dto.ErrorResponse;
import com.example.websample.exception.ErrorCode;
import com.example.websample.exception.WebSampleException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@Slf4j
@RestController
public class SampleController {

    @RequestMapping(value = "/order/{orderId}", method = RequestMethod.GET)
    public String getOrder(@PathVariable("orderId") String id) throws IllegalAccessException, SQLIntegrityConstraintViolationException {
        log.info("Get some order : " + id);
        if ("500".equals(id)) {
            throw new WebSampleException(
                    ErrorCode.TOO_BIG_ID_ERROR,
                    "500 is too big orderId"
            );
        }
        if ("3".equals(id)) {
            throw new WebSampleException(
                    ErrorCode.TOO_SMALL_ID_ERROR,
                    "3 is too small orderId"
            );
        }
        if ("4".equals(id)) {
            throw new SQLIntegrityConstraintViolationException(
                    "Duplicated insertion is occurred"
            );
        }
        return "orderId:" + id + ", orderAmount:1000";
    }

    @DeleteMapping(value = "/order/{orderId}")
    public String deleteOrder(@PathVariable("orderId") String id){
        log.info("Delete some order : " + id);

        return "delete Id:" + id;
    }

    @GetMapping("/order")
    public String getOrderWithRequestParam(
            // required 기본 True, 디폴트값 지정가능
            @RequestParam(value = "orderId", required = false, defaultValue = "defaultId") String id,
            @RequestParam("orderAmount") String amount
    ) {
        log.info("Get order: " + id + ", amoumt : " + amount);
        return "orderId:" + id + "," + "orderAmount:" + amount;
    }

    @GetMapping("/order/2")
    public String getOrder2() {
        log.info("Get some order2");
        return "orderId:1, orderAmount:1000";
    }

    @PostMapping("/order")
    public String createOrder(
            @RequestBody CreateOrderRequest createOrderRequest,
            @RequestHeader String userAccountId
    ) {
        log.info("Create order: " + createOrderRequest + ", userAccountId : " + userAccountId);
        return "orderId:" + createOrderRequest.getOrderId() + "," + "orderAmount:" + createOrderRequest.getOrderAmount();
    }

    //자바 빈 객체로 만들어줘서 get,set다 만들어줌
    @Data
    public static class CreateOrderRequest {
        private String orderId;
        private Integer orderAmount;
    }
}
