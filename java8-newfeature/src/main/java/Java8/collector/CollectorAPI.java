package Java8.collector;

import Java8.lambdaExpress.Apple;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: siteFounder
 */
public class CollectorAPI {

    public static void main(String[] args) {
        List<Apple> apples= Arrays.asList(
                new Apple("red",100),new Apple("green",200),
                new Apple("blue",150),new Apple("blue",300),
                new Apple("red",240),new Apple("green",250)
        );

        averageDouble(apples);
        averageInt(apples);
        averageLong(apples);
        collectingAndThen(apples);
        counting(apples);
        System.out.println("==================groupBy====================");
        groupByFunction(apples);
        groupByFunctionAndCollector(apples);
        groupByFunctionAndSupplierAndCollector(apples);
        summarizing(apples);
        groupingByConcurrentWithFunction(apples);
        groupingByConcurrentWithFunctionAndCollector(apples);
        groupingByConcurrentWithFunctionAndSupplierAndCollector(apples);
        System.out.println("======================================");
        joinWithDelimiter(apples);
        mapping(apples);
        partitioningBy(apples);
        reduce(apples);
        System.out.println("==================ConcurrentMap====================");
        toConcurrentMap(apples);
        toConcurrentMapWithBinaryOperator(apples);
        toConcurrentMapWithBinaryOperatorAndSupplier(apples);


    }

    /**
     *  求平均值
     * @param list
     */
    private static void  averageDouble(List<Apple> list){
        Optional.ofNullable(list.stream().collect(Collectors.averagingDouble(Apple::getWeight))).ifPresent(System.out::println);
    }
    private static void  averageInt(List<Apple> list){
        Optional.ofNullable(list.stream().collect(Collectors.averagingInt(Apple::getWeight))).ifPresent(System.out::println);
    }
    private static void  averageLong(List<Apple> list){
        Optional.ofNullable(list.stream().collect(Collectors.averagingLong(Apple::getWeight))).ifPresent(System.out::println);
    }

    /**
     * 收集结果，然后处理。
     * public static<T,A,R,RR> Collector<T,A,RR> collectingAndThen(Collector<T,A,R> downstream,
     *                                                                 Function<R,RR> finisher)
     *  downstream: target Collectors
     *  finisher: handle downstream
     *
     * @param list
     */
    private static void collectingAndThen(List<Apple> list){
        Optional.ofNullable(list.stream().filter(a->a.getColor().equals("red")).
                collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableCollection)))
                .ifPresent(System.out::println);
    }

    /**
     * 集合计数
     * @param list
     */
    private static void counting(List<Apple> list){
        Optional.ofNullable(list.stream().collect(Collectors.counting())).ifPresent(System.out::println);
    }

    /**
     * groupBy Function
     * groupingBy(Function<? super T, ? extends K> classifier)
     * @param list
     */
    private static void groupByFunction(List<Apple> list){
        Optional.ofNullable(list.stream().collect(Collectors.groupingBy(Apple::getColor))).ifPresent(System.out::println);
    }

    /**
     * groupingBy(Function<? super T, ? extends K> classifier,
     *            Collector<? super T, A, D> downstream)
     * @param list
     */
    private static void groupByFunctionAndCollector(List<Apple> list){
        Optional.ofNullable(list.stream().collect(Collectors.groupingBy(Apple::getColor,Collectors.averagingDouble(Apple::getWeight))))
                .ifPresent(System.out::println);
    }

    /**
     * groupingBy(Function<? super T, ? extends K> classifier,
     *            Supplier<M> mapFactory,
     *            Collector<? super T, A, D> downstream)
     * @param list
     */
    private static void groupByFunctionAndSupplierAndCollector(List<Apple> list){
        Map map=list.stream().collect(Collectors.groupingBy(Apple::getColor, TreeMap::new,Collectors.averagingDouble(Apple::getWeight)));
        Optional.ofNullable(map.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(map).ifPresent(System.out::println);
    }

    /**
     * 统计
     *  Collector<T, ?, DoubleSummaryStatistics> summarizingDouble(ToDoubleFunction<? super T> mapper)
     *  Collector<T, ?, LongSummaryStatistics> summarizingLong(ToLongFunction<? super T> mapper)
     * @param apples
     */
    private static void summarizing(List<Apple> apples){
        DoubleSummaryStatistics collect = apples.stream().collect(Collectors.summarizingDouble(Apple::getWeight));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    /**
     * result Collection(hashMap) converted to ConcurrentHashMap
     * @param apples
     */
    private static void groupingByConcurrentWithFunction(List<Apple> apples){
        ConcurrentMap<String, List<Apple>> collect = apples.stream().collect(Collectors.groupingByConcurrent(Apple::getColor));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    /**
     * Collection(hashMap) converted to ConcurrentHashMap
     * Collector<T, ?, ConcurrentMap<K, D>> groupingByConcurrent(Function<? super T, ? extends K> classifier,
     *                                                           Collector<? super T, A, D> downstream)
     * @param apples
     */
    private static void groupingByConcurrentWithFunctionAndCollector(List<Apple> apples){
        ConcurrentMap<String, Double> collect = apples.stream().collect(Collectors.groupingByConcurrent(Apple::getColor, Collectors.averagingDouble(Apple::getWeight)));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);//{red=150.0, green=175.0, blue=125.0}
    }

    /**
     * Collection(hashMap) converted to ConcurrentHashMap
     * Collector<T, ?, M> groupingByConcurrent(Function<? super T, ? extends K> classifier,
     *                                         Supplier<M> mapFactory,
     *                                         Collector<? super T, A, D> downstream)
     * @param apples
     */
    private static void groupingByConcurrentWithFunctionAndSupplierAndCollector(List<Apple> apples){
        ConcurrentSkipListMap<String, Double> collect = apples.stream().collect(Collectors.groupingByConcurrent(Apple::getColor, ConcurrentSkipListMap::new, Collectors.averagingDouble(Apple::getWeight)));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    /**
     * join
     * public static Collector<CharSequence, ?, String> joining(CharSequence delimiter,
     *                                                          CharSequence prefix,
     *                                                          CharSequence suffix)
     * @param apples
     */
    private static void joinWithDelimiter(List<Apple> apples){
        Optional.ofNullable(apples.stream().map(Apple::getColor).collect(Collectors.joining(",","Color[","]")))
        .ifPresent(System.out::println);//Color[red,green,blue,blue,red,green]
    }

    /**
     * Collector<T, ?, R> mapping(Function<? super T, ? extends U> mapper,
     *                                Collector<? super U, A, R> downstream)
     * @param apples
     */
    private static void mapping(List<Apple> apples){
        Optional.ofNullable(apples.stream().collect(Collectors.mapping(Apple::getColor,Collectors.joining(",","Color[","]"))))
        .ifPresent(System.out::println);
    }

    /**
     * Collector<T, ?, Map<Boolean, List<T>>> partitioningBy(Predicate<? super T> predicate)
     * @param apples
     */
    private static void partitioningBy(List<Apple> apples){
        Map<Boolean, List<Apple>> collect = apples.stream().collect(Collectors.partitioningBy(a -> "red".equals(a.getColor())));
        Optional.ofNullable(collect.getClass()).ifPresent(System.out::println);
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    /**
     * Collector<T, ?, U> reducing(U identity, //标 识
     *                            Function<? super T, ? extends U> mapper,
     *                            BinaryOperator<U> op)
     * @param apples
     */
    private static void reduce(List<Apple> apples){
        Integer collect = apples.stream().collect(Collectors.reducing(0, Apple::getWeight, BinaryOperator.maxBy(Integer::compareTo)));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    /**
     * Collector<T, ?, ConcurrentMap<K,U>> toConcurrentMap(Function<? super T, ? extends K> keyMapper,
     *                                                     Function<? super T, ? extends U> valueMapper)
     * @param apples
     */
    private static void toConcurrentMap(List<Apple> apples){
        ConcurrentMap< Integer,String> collect = apples.stream().collect(Collectors.toConcurrentMap(Apple::getWeight,Apple::getColor));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    /**
     * toConcurrentMap(Function<? super T, ? extends K> keyMapper,
     *                 Function<? super T, ? extends U> valueMapper,
     *                 BinaryOperator<U> mergeFunction)
     *
     * @param apples
     */
    private static void toConcurrentMapWithBinaryOperator(List<Apple> apples){
        ConcurrentMap<String, Integer> collect = apples.stream().collect(Collectors.toConcurrentMap(Apple::getColor, v -> 1, (a, b) -> a + b));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }

    /**
     * Collector<T, ?, M> toConcurrentMap(Function<? super T, ? extends K> keyMapper,
     *                                    Function<? super T, ? extends U> valueMapper,
     *                                    BinaryOperator<U> mergeFunction,
     *                                    Supplier<M> mapSupplier)
     * @param apples
     */
    private static void toConcurrentMapWithBinaryOperatorAndSupplier(List<Apple> apples){
        ConcurrentSkipListMap<String, Integer> collect = apples.stream().collect(Collectors.toConcurrentMap(Apple::getColor, v -> 1, (a, b) -> a + b, ConcurrentSkipListMap::new));
        Optional.ofNullable(collect).ifPresent(System.out::println);
    }


    }
