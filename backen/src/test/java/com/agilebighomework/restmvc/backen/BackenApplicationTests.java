package com.agilebighomework.restmvc.backen;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
class BackenApplicationTests {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void addTask() {
        Task t=new Task("3", "test", new Date());
        ResponseEntity<String> resp = testRestTemplate.postForEntity("/api/task", t, String.class);
        Assert.assertEquals(resp.getBody(), "success");
        Assert.assertEquals(resp.getStatusCode(), HttpStatus.OK);
    }

}
