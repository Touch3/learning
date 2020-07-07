package Java8.optional;

import Java8.methodReference.Person;

import java.util.Optional;

/**
 * @Description:
 * @Author: siteFounder
 */
public class OptionalUsage {

    public static void main(String[] args) {

        Optional<String> optional=Optional.empty();
        //String res = optional.get(); // java.util.NoSuchElementException: No value present

        //of() -> non-null value , ofNullable -> Nullable
        Optional<String> optional1=Optional.of(new String());

        //return value == null ? empty() : of(value)
        Optional<String> optional2=Optional.ofNullable("hello");//null
        /*System.out.println(optional2.get());*/ //如果没值则抛异常，处理如下
        //return value != null ? value : other.get();
        String s1=optional2.orElseGet(String::new);//optional2 如果获取不到值，则以一个新值作为获取值
        System.out.println("s1: "+s1);
        //return value != null ? value : other;
        String s2=optional2.orElse(new String());
        System.out.println("s2: "+s2);
        // if optional2.get ==null ,throw RuntimeException
        optional2.orElseThrow(RuntimeException::new);

        System.out.println("================================");
        Optional<String> optional4=Optional.ofNullable(null);
        // public Optional<T> filter(Predicate<? super T> predicate)
        String s3=optional4.filter(s -> s.length()>1).orElseGet(()-> "DEFAULT");
        System.out.println("s3: "+s3);
        //public<U> Optional<U> map(Function<? super T, ? extends U> mapper)
        int s4=optional2.map(String::length).orElse(8);
        System.out.println("s4: "+s4);
        //consumer.accept(value);
        optional2.ifPresent(System.out::println);

        System.out.println("================================");
        String height = getPersonHeightByOptional(new Person());
        System.out.println(height);
    }

    /**
     *
     * @param person
     * @return
     */
    public static String getPersonHeightByOptional(Person person){
        return Optional.ofNullable(person).map(Person::getHeight).orElse("111");
    }

}
