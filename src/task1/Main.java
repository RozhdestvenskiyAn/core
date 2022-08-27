package task1;

public class Main {
    public static void main(String[] args) {
        int[][] array = getArrayWithRandomValues();
        int minValue = getMinValue(array);
        int maxValue = getMaxValue(array);
        double avgValue = getAvgValue(array);

        System.out.println("Минимальное значение " + minValue);
        System.out.println("Максимальное значение " + maxValue);
        System.out.println("Среднее значение " + avgValue);
    }

    private static double getAvgValue(int[][] array) {
        int sum = 0;
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                sum += array[i][j];
                count++;
            }
        }
        return (double) sum / count;
    }

    private static int getMaxValue(int[][] array) {
        int max = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] > max)
                    max = array[i][j];
            }
        }
        return max;
    }

    private static int getMinValue(int[][] array) {
        int min = array[0][0];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i][j] < min)
                    min = array[i][j];
            }
        }
        return min;
    }

    public static int[][] getArrayWithRandomValues() {
        int[][] array = new int[5][5];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                array[i][j] = (int) (Math.random() * (200 + 1) - 100);
            }
        }
        return array;
    }
}
