package Java8.optional;

import java.util.Optional;

/**
 * @Description:
 * @Author: siteFounder
 */
public class Person {

    private Car car;

    private Optional<Car> carOptional;

    public Car getCar() {
        return car;
    }

    /**
     * 由于 person - car - name 封装层数较多，使用 Optional
     * @return
     */
    public Optional<Car> getCarOption() {
        return carOptional;
    }


    class Car{
        private Insurance insurance;

        private Optional<Insurance> insuranceOptional;

        public Insurance getInsurance() {
            return insurance;
        }

        /**
         * 由于 person - car - name 封装层数较多，使用 Optional
         * @return
         */
        public Optional<Insurance> getInsuranceOptional(){
            return insuranceOptional;
        }

    }

    class Insurance{
        private String name;

        public String getName() {
            return name;
        }

    }
}
