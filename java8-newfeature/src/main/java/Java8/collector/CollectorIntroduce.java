package Java8.collector;

import Java8.lambdaExpress.Apple;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: siteFounder
 */
public class CollectorIntroduce {

    public static void main(String[] args) {

        List<Apple> apples= Arrays.asList(
                new Apple("red",100),new Apple("green",100),
                new Apple("blue",100),new Apple("blue",100),
                new Apple("red",100),new Apple("green",100)
        );

        Optional.ofNullable(getGroupByNormal(apples)).ifPresent(System.out::println);
        Optional.ofNullable(getGroupByFunction(apples)).ifPresent(System.out::println);
        Optional.ofNullable(getGroupByCollector(apples)).ifPresent(System.out::println);

    }

    /**
     * 普通方式 对集合中苹果 根据颜色分组
     * @param apples
     * @return
     */
    public static Map<String,List<Apple>> getGroupByNormal(List<Apple> apples){
        Map<String,List<Apple>> map=new HashMap<>();
        for(Apple apple:apples){
            List<Apple> list=map.get(apple.getColor());
            if(null==list){
                list=new ArrayList<>();
                map.put(apple.getColor(),list);
            }
            list.add(apple);
        }
        return map;
    }

    /**
     * 通过 Function 来实现
     * @param apples
     * @return
     */
    public static Map<String,List<Apple>> getGroupByFunction(List<Apple> apples){
        Map<String,List<Apple>> map=new HashMap<>();
        apples.stream().forEach(a-> {
            List<Apple> appleList = Optional.ofNullable(map.get(a.getColor()))
                        .orElseGet(() -> {
                            List<Apple> list = new ArrayList<>();
                            map.put(a.getColor(), list);
                            return list;
                        });
                        appleList.add(a);
        });
        return map;
    }

    /**
     *通过 Collector 来实现
     * @param apples
     * @return
     */
    public static Map<String,List<Apple>> getGroupByCollector(List<Apple> apples){
        return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
    }

}
