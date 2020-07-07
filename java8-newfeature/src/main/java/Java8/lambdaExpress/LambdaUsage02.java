package Java8.lambdaExpress;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * @Description:
 *      Consumer: 对传入的参数做处理
 *         accept： 方法内自定义表达式
 * @Author: siteFounder
 */
public class LambdaUsage02 {

    /**
     * 对传入的参数做处理
     * @param apples
     * @param consumer
     */
    public static void consumer(List<Apple> apples, Consumer<Apple> consumer) {
        for (Apple apple:apples){
            consumer.accept(apple);
        }
    }

    /**
     * BiConsumer<T, U>
     *     void accept(T t, U u)
     * @param apples
     * @param consumer
     */
    public static void BiConsumer(List<Apple> apples, BiConsumer<String,Integer> consumer) {
        for (Apple apple:apples){
            consumer.accept(apple.getColor(),apple.getWeight());
        }
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("green",160),new Apple("red",140));

        consumer(apples,apple -> System.out.println(apple.getWeight()+15));
        BiConsumer(apples,(c,w)-> System.out.println("color: "+c+"\tweight: "+w));
    }

}
