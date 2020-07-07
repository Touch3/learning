package Java8.lambdaExpress;

import java.util.Comparator;

/**
 * @Description:
 *          (parameters)-> expression
 *          (parameters)-> {statement;}
 * @Author: siteFounder
 */
public class LambdaExpress {

    public static void main(String[] args) {

        //Comparator<Apple> c1 =
        new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getColor().compareTo(o2.getColor());
            }
        };
        //使用lambda表达式表示为 ： (o1,o2) -> o1.getColor().compareTo(o2.getColor())



        // lambda 表达式(两个参数 + return ):两种方式
        //                    参数列表  ->     lambda body
        Comparator<Apple> c2 =(o1,o2) -> o1.getColor().compareTo(o2.getColor());
        Comparator<Apple> c3 =(o1,o2) ->{return o1.getColor().compareTo(o2.getColor());};

    }
}
