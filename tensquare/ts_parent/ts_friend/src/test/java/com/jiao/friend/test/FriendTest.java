package com.jiao.friend.test;

import com.jiao.friend.service.FriendService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by jiao on 12/15/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendTest {

    @Autowired
    private FriendService friendService;

    @Test
    @Rollback
    public void test1(){
//        friendService.addFriendTest("1","2","0");
    }
}
