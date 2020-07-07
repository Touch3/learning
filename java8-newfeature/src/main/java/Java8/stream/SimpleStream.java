package Java8.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: siteFounder
 */
public class SimpleStream {

    public static void main(String[] args) {
        List<Dash> menus= Arrays.asList(
                new Dash("meat",500),
                new Dash("apple",200),
                new Dash("rice",180),
                new Dash("fish",450),
                new Dash("chicken",480)
        );

//        List<String> list = getDashByCollection(menus);
//        System.out.println(list);
//        List<String> list1 =getDashByStream(menus);
//        System.out.println(list1);

        /**
         * parallelStream(): 并行流 对 collection 中的元素并行操作
         * stream(): 顺序流，对 collection 中的元素逐个操作
         */
        List<String> list2=
        menus.parallelStream()
                .filter(dash -> {
            System.out.println("filter: "+dash.getName());
            return dash.getCaloes()>400;})
                .map(dash -> {
                    System.out.println("map: "+dash.getName());
                    return dash.getName();
                })
                .limit(3).collect(Collectors.toList());

        System.out.println(list2);

        Stream<Dash> stream=Stream.of(new Dash("meat",500), new Dash("apple",200));
        stream.forEach(System.out::println);

    }

    /**
     *  通过 Stream 的方式进行
     *  1、Stream<T> filter(Predicate<? super T> predicate)
     *          Predicate.test() -> boolean
     *  2、Stream<T> sorted(Comparator<? super T> comparator)
     *          Comparator.comparing() -> Comparator
     *  3、<R> Stream<R> map(Function<? super T, ? extends R> mapper)
     *          Function<T , R>
     *  4、<R, A> R collect(Collector<? super T, A, R> collector)
     *          Collector<? super T, A, R> collector：转换为一种集合类型
     *  5、Stream<T> limit(long maxSize)
     *          仅仅取其中的 maxSize 元素
     *  6、void forEach(Consumer<? super T> action)
     *          Consumer<? super T> action ——> void accept(T t)
     *  7、public static<T> Stream<T> of(T... values)
     *          枚举一些元素
     * @param dashes
     * @return
     */
    private static List<String> getDashByStream(List<Dash> dashes){
        return dashes.stream().filter(d -> d.getCaloes()>400).sorted(Comparator.comparing(Dash::getCaloes)).
                map(Dash::getName).limit(3).collect(Collectors.toList());
    }

    /**
     * 通过 Collection 的方式：选择 、 排序 、 操作
     * @param dashes
     * @return
     */
    private static List<String> getDashByCollection(List<Dash> dashes){
        List<Dash> lowDash=new ArrayList<>();
        //filter
        for(Dash dash:dashes){
            if(dash.getCaloes()>400){
                lowDash.add(dash);
            }
        }
        //sorted
        Collections.sort(lowDash, Comparator.comparing(Dash::getCaloes));
        List<String> dashName=new ArrayList<>();
        //map
        for(Dash dash:lowDash){
            dashName.add(dash.getName());
        }
        return dashName;
    }

}
