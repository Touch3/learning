package Java8.lambdaExpress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;

/**
 * @Description:
 *            Predicate :   断言
 *               boolean test(T t)
 * @Author: siteFounder
 */
public class LambdaUsage01 {

    /**
     * Predicate<T>
     *     boolean test(T t)
     * @param apples 断言对象
     * @param predicate
     * @return
     */
    public static List<Apple> filter(List<Apple> apples, Predicate<Apple> predicate) {
        List<Apple> list=new ArrayList<>();
        for (Apple apple:apples){
            if (predicate.test(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    /**
     * LongPredicate
     *         boolean test(long value)
     * @param apples
     * @param predicate
     * @return
     */
    public static List<Apple> weightFilter(List<Apple> apples, LongPredicate predicate) {
        List<Apple> list=new ArrayList<>();
        for (Apple apple:apples){
            if (predicate.test(apple.getWeight())) {
                list.add(apple);
            }
        }
        return list;
    }

    /**
     * BiPredicate<T, U> ：多参数作为条件
     *      boolean test(T t, U u)
     * @param apples
     * @param predicate
     * @return
     */
    public static List<Apple> BiFilter(List<Apple> apples, BiPredicate<String,Integer> predicate) {
        List<Apple> list=new ArrayList<>();
        for (Apple apple:apples){
            if (predicate.test(apple.getColor(),apple.getWeight())) {
                list.add(apple);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("green",160),new Apple("red",140));
        //Predicate<T>
        List<Apple> res=filter(apples,apple->apple.getColor().equals("green"));
        //LongPredicate
        List<Apple> res1=weightFilter(apples,w -> w<150);
        //BiPredicate<T,U>
        List<Apple> res2=BiFilter(apples,(c,w)->c.equals("green")&&w>150);

        System.out.println(res);
        System.out.println(res1);
        System.out.println(res2);

    }

}
