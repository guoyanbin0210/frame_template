package com.lt.body.websocket;

import com.lt.base.controller.BaseController;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * Created with GaoShan.
 * Create Time: 2019/9/11 14:05
 * Description:
 */
@RestController
@Log4j2
@ApiIgnore
public class WebSocketController extends BaseController {

    @Resource
    private MyWebSocketHandler myWebSocketHandler;

    @GetMapping("/api_p/webSocket/sendAllMessage")
    public void sendAllMessage(String msg){
        myWebSocketHandler.sendAllMessage(msg);

    }
}
