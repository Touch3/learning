package Java8.methodReference;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @Description:
 *          构造方法引用：functionInterface = > Class::new
 *             组成语法格式：Class::new
 *             String::new， 等价于lambda表达式  () -> new String()
 * @Author: siteFounder
 */
public class MethodReference03 {

    public static void main(String[] args) {
        Supplier<String> supplier = String::new;
        System.out.println(supplier.get().getClass().getName());

        Supplier<Person> supplier1=Person::new;
        System.out.println(supplier1.get().getClass().getName());

        BiFunction<String,String,Person> function=Person::new;
        Person p = function.apply("20","30");
        System.out.println(p.toString());

    }
}
