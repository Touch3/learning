package com.example.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description:
 * @Author: siteFounder
 */
@Data
@Component
@ConfigurationProperties("app")
public class Config {
      private List<String> list;
      private String rootShell;
}
