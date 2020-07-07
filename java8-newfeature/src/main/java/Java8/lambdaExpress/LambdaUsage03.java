package Java8.lambdaExpress;

import java.util.function.Function;

/**
 * @Description:
 *      function<T,R>
 *          R apply<T> ：两种范型对应的规则
 *          返回值为：R类型
 *          入参类型：T
 *
 * @Author: siteFounder
 */
public class LambdaUsage03 {

    /**
     * @param apple
     * @param function:功能函数，
     * @return
     */
    public static String function(Apple apple, Function<Apple,String> function) {
        return function.apply(apple);
    }

    public static void main(String[] args) {
        Apple apple =new Apple("yellow",120);
        String res=function(apple,apple1 -> apple1.toString());
        System.out.println(res);

    }

}
