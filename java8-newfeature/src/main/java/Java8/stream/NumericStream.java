package Java8.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Description:
 *        NumericStream 比 ObjectStream 更节省空间 。
 * @Author: siteFounder
 */
public class NumericStream {

    public static void main(String[] args) {

        //ObjectStream -> NumericStream
        Stream<Integer> integerStream= Arrays.asList(1,2,3,4,5).stream();
        IntStream intStream=integerStream.mapToInt(Integer::intValue);

        int a=9;
        // intStream 在进行操作时的装箱（boxed()）操作
        IntStream.rangeClosed(1,100).filter(b->Math.sqrt(a*a+b*b)%1==0)
//                .boxed()
//                .map(b->new int[]{a,b,(int)Math.sqrt(a*a+b*b)})
                .mapToObj(b->new int[]{a,b,(int)Math.sqrt(a*a+b*b)})
                .forEach(r-> System.out.println("a="+r[0]+" b="+r[1]+" c="+r[2]));
    }
}
