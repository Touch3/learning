package Java8.optional;

import java.util.Optional;

/**
 * @Description:
 * @Author: siteFounder
 */
public class OptionalInAction {

    public static void main(String[] args) {

        Optional.ofNullable(getInsuranceName(null)).ifPresent(System.out::println);//UNKNOWN
        Optional.ofNullable(getInsuranceNamOptional(null)).ifPresent(System.out::println);//OPTIONAL
        Optional.ofNullable(getInsuranceNamOptional(new Person())).ifPresent(System.out::println);//java.lang.NullPointerException

    }

    public static String getInsuranceName(Person person){
        return Optional.ofNullable(person)
                .map(Person::getCar)
                .map(Person.Car::getInsurance)
                .map(Person.Insurance::getName)
                .orElse("UNKNOWN");
    }

    /**
     * public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper)
     * flatMap 扁平化： Optional<Optional<U>>  ->  Optional<U>
     * @param person
     * @return  : return Objects.requireNonNull(mapper.apply(value)) ：要求非空
     */
    public static String getInsuranceNamOptional(Person person){
        return  Optional.ofNullable(person)
                        .flatMap(Person::getCarOption)
                        .flatMap(Person.Car::getInsuranceOptional)
                        .map(Person.Insurance::getName).orElse("OPTIONAL");
    }

}
