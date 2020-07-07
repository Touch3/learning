package Java8.collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @Description:
 *          自定义 List
 * @Author: siteFounder
 */
public class ToListCollector<T> implements Collector<T, List<T>,List<T>> {

    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1,list2)->{
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        //return t->t;
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH ));
    }

    /**
     * test
     * @param args
     */
    public static void main(String[] args) {
        Collector<String,List<String>,List<String>> collector=new ToListCollector<>();
        String[] strings={"ang","hua","isa"};
        List<String> collect = Arrays.stream(strings).collect(collector);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }
}
