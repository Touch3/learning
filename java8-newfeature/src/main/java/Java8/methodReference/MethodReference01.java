package Java8.methodReference;


import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @Description: 方法引用,将方法作为作为入参传入
 *  静态方法引用： static
 *
 * @Author: siteFounder
 */
public class MethodReference01 {

    @FunctionalInterface  //功能接口
    interface StringFunc {
        /**一般 java8 提供的FunctionalInterface不能满足时，可以进行自定义；
         * 进行方法推导传入功能接口函数 的参数方法签名 要与 被引用的静态方法签名一样
         * @param s
         * @return
         */
        String func(String s);
    }

    /**
     * 静态方法： 被引用的方法签名要与 功能函数的方法签名一样   String func(String s)
     * MethodReference.strReverse() -> MethodReference::strReverse
     * @param str
     * @return
     */
    public static String strReverse(String str) {
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            result += str.charAt(i);
        }
        return result;
    }

    /**
     * 实际调用方法，格式：
     *   引用函数 + 参数
     * @param sf
     * @param s
     * @return
     */
    public static String stringOp(StringFunc sf, String s) {
        return sf.func(s);
    }

    /**
     * 使用 java提供的 FunctionalInterface 模型替代 自定义FunctionalInterface
     * @param function
     * @param s
     * @return
     */
    public static String stringOp1(Function<String,String> function,String s){
        return function.apply(s);
    }

    public static void main(String[] args) {
        String inStr = "lambda add power to Java";
//        MyStringOps::strReverse 相当于实现了接口方法func()，并在接口方法func()中作了MyStringOps.strReverse()操作。
//        StringFunc func = MethodReference::strReverse=(str)->MethodReference01.strReverse(str)
        String outStr = stringOp(MethodReference01::strReverse, inStr);

        Function<String,String> func = MethodReference01::strReverse; //对象的计算结果为 方法引用
        String outStr1 = stringOp1(func, inStr);

//        System.out.println("out: "+out);
        System.out.println(outStr.equals(outStr1));

//        BiFunction<String,Integer,Character> function1=(s,i)->s.charAt(i);
//        两个输入一个输出（return）
        BiFunction<String,Integer,Character> function=String::charAt;
        Character c=function.apply("hello",1);
        System.out.println(c);



    }
}
