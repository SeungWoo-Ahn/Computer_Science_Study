package ArrayList;

public class MArrayListTest {
    public static void main(String[] args) {
        MArrayList<Integer> list = new MArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1, 4);
        list.add(5);
        list.add(3, 6);
        System.out.println(list);

        list.remove(1);
        list.remove(Integer.valueOf(5));
        System.out.println(list);

        list.set(1, 7);
        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.contains(3));
        System.out.println(list.lastIndexOf(6));
    }
}
