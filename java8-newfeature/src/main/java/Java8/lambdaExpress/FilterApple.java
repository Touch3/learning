package Java8.lambdaExpress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: 根据苹果属性选择指定的苹果
 * @Author: siteFounder
 */
public class FilterApple {

    @FunctionalInterface  // Callable、Runnable、Comparator ,函数式接口
    interface Filter{
        boolean filter(Apple apple);
    }

    /**  在 filter 自定义选择条件
     *   缺点：每次定义条件时都会写一个实现类 Impl
     */
    static class ColorFilter implements Filter{
        @Override
        public boolean filter(Apple apple) {
            return "green".equals(apple.getColor());
        }
    }

    /**
     * @param apples
     * @param filter 在 filter 自定义选择条件
     * @return
     */
    public static List<Apple> findApple(List<Apple> apples,Filter filter){
        List<Apple> list=new ArrayList<Apple>();
        for(Apple apple:apples){
            if(filter.filter(apple)){
                list.add(apple);
            }
        }
        return list;
    }


    public static void main(String[] args) {
        List<Apple> list= Arrays.asList(new Apple("green",120),new Apple("red",150));

        //普通用法，通过实现类完成
//        List filterList=findApple(list,new ColorFilter());

        // lambda 表达式的使用
//        List<Apple> filterList1= findApple(list,(Apple apple)->{
        List<Apple> filterList1= findApple(list,apple->{  // 当只有一个参数时
            return "green".equals(apple.getColor());
        });

        // 去了 return 后，同时也去掉打括号，与上面等价
        List<Apple> filterList2=findApple(list,apple -> "green".equals(apple.getColor()) );

        System.out.println(filterList1.toString());
        System.out.println(filterList2.toString());

    }


}
