package Java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: siteFounder
 */
public class StreamMap {

    public static void main(String[] args) {
        //基本使用
        List<Integer> list= Arrays.asList(1,2,3,4,5,6,7,7,8,8,9);
        List<String> result =list.stream().map(i->i+":").collect(Collectors.toList());
        System.out.println(result); //[1:, 2:, 3:, 4:, 5:, 6:, 7:, 7:, 8:, 8:, 9:]

        /**
         * flatMap 与 map 的区别 ：扁平化（ List<String[]> -> List<String> ）
         * 所有使用 map(Array::stream) 时生成的单个流被合并起来，即扁平化为一个流。
         */

        String[] words = new String[]{"Hello","World"};
        System.out.println("================map===============");
        List<String[]> a = Arrays.stream(words)
                .map(w -> w.split(""))
                .distinct()
                .collect(Collectors.toList());
        a.forEach(System.out::print); //[Ljava.lang.String;@5b6f7412[Ljava.lang.String;@27973e9b

        System.out.println("\n===========flatMap()===============");
        List<String> b = Arrays.stream(words)
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        b.forEach(System.out::print); //HeloWrd

    }
}
