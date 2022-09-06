package task3;

import java.util.*;
import java.util.stream.Collectors;

public class ComplexExamples {

    static class Person {
        final int id;
        final String name;

        Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Person person)) return false;
            return getId() == person.getId() && getName().equals(person.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getId(), getName());
        }


    }

    private static Person[] RAW_DATA = new Person[]{
            new Person(0, "Harry"),
            new Person(0, "Harry"), // дубликат
            new Person(1, "Harry"), // тёзка
            new Person(2, "Harry"),
            new Person(3, "Emily"),
            new Person(4, "Jack"),
            new Person(4, "Jack"),
            new Person(5, "Amelia"),
            new Person(5, "Amelia"),
            new Person(6, "Amelia"),
            new Person(7, "Amelia"),
            new Person(8, "Amelia"),
    };

    private static final int[] ARRAY = new int[] {3, 4, 2, 7};


        /*  Raw data:

        0 - Harry
        0 - Harry
        1 - Harry
        2 - Harry
        3 - Emily
        4 - Jack
        4 - Jack
        5 - Amelia
        5 - Amelia
        6 - Amelia
        7 - Amelia
        8 - Amelia

        **************************************************

        Duplicate filtered, grouped by name, sorted by name and id:

        Amelia:
        1 - Amelia (5)
        2 - Amelia (6)
        3 - Amelia (7)
        4 - Amelia (8)
        Emily:
        1 - Emily (3)
        Harry:
        1 - Harry (0)
        2 - Harry (1)
        3 - Harry (2)
        Jack:
        1 - Jack (4)
     */

    public static void main(String[] args) {
        System.out.println("Raw data:");
        System.out.println();

        Set<Person> collect = Arrays.stream(RAW_DATA).collect(Collectors.toSet());
        for (Person person : RAW_DATA) {
            System.out.println(person.id + " - " + person.name);
        }

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("Duplicate filtered, grouped by name, sorted by name and id:");
        System.out.println();

        groupingByName(RAW_DATA);

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("print the pair in parentheses, which give the sum 10");
        System.out.println();

        getPairOfValues(ARRAY, 10);

        System.out.println();
        System.out.println("**************************************************");
        System.out.println();
        System.out.println("fuzzy search");
        System.out.println();

        fuzzySearch("car", "ca6$$#_rtwheel"); // true
        fuzzySearch("cwhl", "cartwheel"); // true
        fuzzySearch("cwhee", "cartwheel"); // true
        fuzzySearch("cartwheel", "cartwheel"); // true
        fuzzySearch("cwheeel", "cartwheel"); // false
        fuzzySearch("lw", "cartwheel"); // false
        /*
        Task1
            Убрать дубликаты, отсортировать по идентификатору, сгруппировать по имени

            Что должно получиться Key: Amelia
                Value:4
                Key: Emily
                Value:1
                Key: Harry
                Value:3
                Key: Jack
                Value:1
         */



        /*
        Task2

            [3, 4, 2, 7], 10 -> [3, 7] - вывести пару менно в скобках, которые дают сумму - 10
         */



        /*
        Task3
            Реализовать функцию нечеткого поиска
            
                    fuzzySearch("car", "ca6$$#_rtwheel"); // true
                    fuzzySearch("cwhl", "cartwheel"); // true
                    fuzzySearch("cwhee", "cartwheel"); // true
                    fuzzySearch("cartwheel", "cartwheel"); // true
                    fuzzySearch("cwheeel", "cartwheel"); // false
                    fuzzySearch("lw", "cartwheel"); // false
         */


    }

    private static void fuzzySearch(String first, String second) {
        boolean res = false;
        if (first.length()>second.length()){
            System.out.println(res);
            return;
        }
        if (first.equals(second)){
            res = true;
            System.out.println(res);
            return;
        }
        int currentJ=0;
        for (int i = 0; i < first.length(); i++) {
            for (int j = currentJ; j < second.length(); j++) {
                if (second.charAt(j) == first.charAt(i)){
                    if (i==first.length()-1){
                        res=true;
                    }
                    currentJ = j+1;
                    break;
                }
            }
            if (currentJ==second.length()-1){
                break;
            }
        }
        System.out.println(res);
    }

    private static void getPairOfValues(int[] array, int sum) {
        Arrays.sort(array);

        for (int i=0, j=array.length-1; i<j;){
            if (array[i]+array[j]>sum){
                j--;
                continue;
            }
            if (array[i]+array[j]<sum){
                i++;
                continue;
            }
            if (array[i]+array[j]==sum) {
                System.out.println("[" + array[i] + ", " + array[j] + "]");
                break;
            }
        }

    }

    private static void groupingByName(Person[] rawData) {
        Arrays.stream(rawData)
                .distinct()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Person::getName, TreeMap::new, Collectors.counting()))
                .forEach((key, value) -> System.out.println("Key: " + key + "\nValue: " + value));
    }
}
