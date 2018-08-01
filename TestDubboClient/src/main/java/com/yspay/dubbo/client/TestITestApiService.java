package com.yspay.dubbo.client;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import yspay.trade.order.api.ITestApiService;

import com.yspay.base.BaseTest;

public class TestITestApiService extends BaseTest {

    private static final Log logger = LogFactory.getLog(TestITestApiService.class);


    @Test
    public void testQueryInfo() {
        try {
            String reqUid = UUID.randomUUID().toString().replaceAll("-", "")
                    .toUpperCase();
            String msg = "test iface queryInfo ";
            int sleeptime = 2;
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testQueryInfoValidParam() {
        try {
            String reqUid = UUID.randomUUID().toString().replaceAll("-", "")
                    .toUpperCase();
            String msg = "test iface queryInfoValidParam ";
            // msg = null;
            int sleeptime = 2;
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}