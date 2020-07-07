package Java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: siteFounder
 */
public class StreamFilter {

    /**
     * Stream<T> filter(Predicate<? super T> predicate)
     *
     * Stream<T> skip(long n)
     *
     * Stream<T> limit(long maxSize)
     *
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7,7,8,8,9);

        List<Integer> integers=list.stream().filter(i->i%2==0).collect(Collectors.toList());
        System.out.println(integers); //[2, 4, 6, 8, 8]
        integers=list.stream().skip(5).collect(Collectors.toList());
        System.out.println(integers);//[6, 7, 7, 8, 8, 9]
        integers=list.stream().limit(5).collect(Collectors.toList());
        System.out.println(integers);//[1, 2, 3, 4, 5]
    }
}
