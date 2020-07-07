package Java8.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Description:
 * @Author: siteFounder
 */
public class CreateStream {

    public static void main(String[] args) {
//        createStreamFromCollection().forEach(System.out::println);
//        createStreamFromValues().forEach(System.out::println);
//        createStreamFromArrays().forEach(System.out::println);
//        createStreamFromFile();
        createStreamFromIterator().forEach(System.out::println);
    }

    /**
     * create Stream by Collection
     * @return
     */
    public static Stream<String> createStreamFromCollection(){
        List<String> list = Arrays.asList("hello","world","!","Collection");
        return list.stream();
    }

    /**
     * create Stream by values
     * @return
     */
    public static Stream<String> createStreamFromValues(){
        return Stream.of("hello","world","!","values");
    }

    /**
     * create Stream by array
     * @return
     */
    public static Stream<String> createStreamFromArrays(){
        String[] array = {"array"};
        return Arrays.stream(array);
    }

    /**
     * create Stream by File
     * @return
     */
    public static Stream<String> createStreamFromFile(){
        Path path= Paths.get("/Users/pengweiya/IdeaProjects/java8-newfeature/src/main/java/Java8/stream/CreateStream.java");
        try (Stream<String> fileStream= Files.lines(path)){
            fileStream.forEach(System.out::println);
            return fileStream;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * create Stream by Iterator
     * @return
     */
    public static Stream<Integer> createStreamFromIterator(){
        return Stream.iterate(1,n->n*2).limit(10);
    }
}
