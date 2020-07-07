package Java8.methodReference;

import java.util.Arrays;

/**
 * @Description:
 *     实例方法引用： 新建对象实例
 * @Author: siteFounder
 */
public class MethodReference02 {

    /**
     * 实例方法的 方法引用
     * 模拟 Comparator 的 int compare(T o1, T o2) 方法
     * @param a
     * @param b
     * @return
     */
    public int compareByHeight(Person a, Person b){
        return a.getHeight().compareTo(b.getWeight());
    }

    public static void main(String[] args) {

        MethodReference02 reference02 = new MethodReference02();
        Person[] personList=new Person[]{new Person("a","1"),
                                         new Person("B","1"),
                                         new Person("b","1"),
                                         new Person("D","1")};
        //public static <T> void sort(T[] a, Comparator<? super T> c)
        //Comparator<? super T> c -> reference02::compareByWeight -> (o1,o2)->a.getHeight().compareTo(b.getWeight())
        Arrays.sort(personList,reference02::compareByHeight);
        for (Person p:personList){
            System.out.println(p.getHeight()+":"+p.getWeight());
        }
    }
}
