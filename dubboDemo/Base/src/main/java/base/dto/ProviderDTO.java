package base.dto;

import java.io.Serializable;

/**
 * RPC接口DTO
 * 注意这里要实现序列化接口
 * @Author Sans
 * @CreateTime 2019/11/6 23:04
 */
public class ProviderDTO implements Serializable {
    // ID
    private int id;
    // 名字
    private String name;
    // 序号
    private Integer number;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}