package com.agilebighomework.restmvc.backen;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
    @Autowired
    private TaskService taskService;

    @Test
    void addTask() {
        Task t=new Task("3", "test", new Date());
        ResponseEntity<String> resp = testRestTemplate.postForEntity("/api/task", t, String.class);
        Assert.assertEquals(resp.getBody(), "success");
        Assert.assertEquals(resp.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getAllTask() throws JsonProcessingException {
        Task t = new Task();
        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<String> response = testRestTemplate.getForEntity("/api/tasks/", String.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(response.getBody(), mapper.writeValueAsString(taskService.getDb()));
    }

    @Test
    public void deleteTaskByID() {
        ResponseEntity<String> resp = testRestTemplate.exchange("/api/task/1", HttpMethod.DELETE, null, String.class);
        Assert.assertEquals(resp.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(resp.getBody(), "success");
    }


}
