package task2;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[] array = new int[]{5, 6, 3, 2, 5, 1, 4, 9};
        sort(array);


        //  Test  //

        int[] testArray = {0, 1, 2, 0, 1, 2, 1, 5};
        int[] sortedTestArrayByHands = {0,0,1,1,1,2,2,5};
        assert Arrays.equals(sortedTestArrayByHands,sort(testArray)): "test failed";

    }

    private static int[] sort(int[] array) {
        int x = 0;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    x = array[i];
                    array[i] = array[j];
                    array[j] = x;
                }
            }
        }
        return array;
    }
}
