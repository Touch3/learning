package com.example.demo.service;

import com.example.demo.config.Config;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Description: 静态变量初始化方式，有两种方式：
 * 一：通过构造器方式
 *      当一个类声明参数构造器时，而没有声明无参构造器时，此时通过@Autowired注入的是含参的bean，会初始化其中的静态变量；
 *      当一个类声明参数构造器时，并且  声明无参构造器时，此时通过@Autowired注入的是无参的bean，不会初始化其中的静态变量；
 * 二：使用 @PostConstruct 注解（在构造器的后面执行）
 *      此时必须要@Autowired相应的bean，可以不声明无参构造器
 *
 * Java类初始化优先顺序：
 *     本类中：静态变量 > 静态初始化块 > 变量 > 初始化块 > 构造器
 *     含有父类：父（静态变量、静态初始化块）> 子（静态变量、静态初始化块） >
 *             父（变量、初始化块、构造器）>（变量、初始化块、构造器）
 *
 * @Author: siteFounder
 */
@Component
@Data
@Slf4j
public class Service {

    @Autowired
     private Config conf;

     public static String rootShell;
     public static List<String> list;


//     public Service(){
//         System.out.println("=====---------------------------------=======");
//     }

    public Service(Config conf) {
        log.info("======------------with param------------=========");
        this.conf=conf;
        rootShell=conf.getRootShell()+"construct";
        list=conf.getList();
        log.info("rootShell: [{}],list: [{}]",rootShell,list);
    }

    @PostConstruct
    public void init(){
        log.info("======------------ init ------------=========");
        rootShell =conf.getRootShell()+"init";
        list=conf.getList();
        log.info("rootShell: [{}],list: [{}]",rootShell,list);
    }

    public String nullStatic(String s){
        log.info("========into method=======");
        return rootShell.concat(s);
    }

    public static int StaticMethod(int d){
        log.info("========into method=======");
        return list.size()+d;
    }

}
