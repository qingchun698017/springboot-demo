//package com.example.demo;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.example.demo.web.HelloWorldController;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class DemoApplicationTests {
//
//    private MockMvc mvc;
//
//    @Before
//    public void setUp() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
//    }
//
//    @Test
//    public void getHello() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/index.html").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//            .andExpect(content().string(equalTo("hello world~")));
//    }
//
//    @Test
//    public void contextLoads() {
//    }
//
//}
