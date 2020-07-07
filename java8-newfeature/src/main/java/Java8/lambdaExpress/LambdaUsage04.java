package Java8.lambdaExpress;

import java.util.function.Supplier;

/**
 * @Description:
 *
 *        Supplier<T>
 *              T get()：范型T为其返回的类型
 * @Author: siteFounder
 */
public class LambdaUsage04 {
    /**
     *
     * @param supplier
     * @return
     */
    public static Apple supplier(Supplier<Apple> supplier){
        return supplier.get();
    }

    public static void main(String[] args) {
        Apple apple=supplier(()->new Apple("red",111));
        System.out.println(apple);

        Supplier<String> supplier=String::new;
        System.out.println(supplier.get().getClass());
    }
}
