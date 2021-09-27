import java.util.Arrays;

public class LinkedBagTest {

public static void main(String[] args) {

BagInterface < String > bag1 = new LinkedBag < >();

BagInterface < String > bag2 = new LinkedBag < >();

bag1.add("A");
bag1.add("B");
bag1.add("A");
bag1.add("C");
bag1.add("B");
bag1.add("C");
bag1.add("D");

bag2.add("A");
bag2.add("A");
bag2.add("B");
bag2.add("A");
bag2.add("C");
bag2.add("A");

System.out.println(Arrays.toString(bag1.union(bag1, bag2).toArray()));

System.out.println(Arrays.toString(bag1.intersection(bag1, bag2).toArray()));

System.out.println(Arrays.toString(bag1.difference(bag1, bag2).toArray()));

}

}