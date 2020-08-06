package com.example.demo.service;

import com.example.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ServiceTest {

    @Autowired
    private Service service;

    @Test
    public void nullStatic() {
        String s=service.nullStatic("++");
        System.out.println(s);
    }

    @Test
    public void staticMethod() {
        int i=Service.StaticMethod(1000);
        System.out.println(i);
    }
}