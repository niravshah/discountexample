package util.iterator;

import java.util.List;

import static org.apache.commons.collections4.ListUtils.sum;


public class ListUtils {
    public static <T> List<T> concatenate(List<T> firstList, List<T> secondList, List<T>... otherLists) {
        List<T> result = sum(firstList, secondList);
        for (List<T> otherList : otherLists) {
            result = sum(result, otherList);
        }
        return result;
    }
}
