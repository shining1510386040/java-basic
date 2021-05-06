package com.demo.threaddesign.crawer;

import com.demo.threaddesign.HttpClientUtils;
import com.demo.threaddesign.Task;
import com.demo.threaddesign.Worker;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 扒票worker
 * @date 2021/4/29 16:21
 * @see
 */
public class TicketWorker extends Worker {

    @Override
    protected Object handle(Task executeTask) {

        HttpClientUtils instance = HttpClientUtils.getInstance();
        String url = "";
        Map params = new HashMap(4);
        String res = instance.sendHttpPost(url, params);


        return null;
    }
}
