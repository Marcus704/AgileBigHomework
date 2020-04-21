package com.agilebighomework.restmvc.backen;

import com.agilebighomework.restmvc.backen.all.Task;
import com.agilebighomework.restmvc.backen.all.TaskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BackenApplicationTests {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private TaskService taskService;

    @Test
    public void addTask() {
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

    @Test
    public void findTaskById() throws JsonProcessingException {
        ResponseEntity<String> resp = testRestTemplate.getForEntity("/api/task/1", String.class);
        Assert.assertEquals(resp.getStatusCode(), HttpStatus.OK);
        ObjectMapper mapper = new ObjectMapper();
        Assert.assertEquals(resp.getBody(), mapper.writeValueAsString(taskService.findById("1")));
    }

    @Test
    public void changeTaskById() {
        Task t = new Task("1", "changed", new Date());
        HttpEntity httpEntity = new HttpEntity(t, null);
        ResponseEntity<String> resp = testRestTemplate.exchange("/api/task/1", HttpMethod.PUT, httpEntity, String.class);
        Assert.assertEquals(resp.getStatusCode(),HttpStatus.OK);
        Assert.assertEquals(resp.getBody(),"success");
    }
}
