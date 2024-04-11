package myStream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * packageName    : MyStream
 * fileName       : FlatMap
 * author         : Yeong-Huns
 * date           : 2024-04-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-04-11        Yeong-Huns       최초 생성
 */
public class MyStream {
    public String returnRepeatedString(String string, int times) {
        return Stream.of(string)
                .flatMap(e-> Arrays.stream(e.split("")))
                .map(a-> String.valueOf(a).repeat(times))
                .reduce("", (a,b)->a+b);
    }

    public void sortAndCount(List<Customer> customers){
            Stream.of(customers)
                    .forEach(i->i.stream()
                            .collect(Collectors.groupingBy(Customer::getFirstName))
                            .entrySet()
                            .stream()
                            .sorted(Comparator.comparingInt(Key->Key.getValue().size()))
                            .forEach(Result->System.out.println(Result.getKey() + " " + Result.getValue().size())));
    }
}
