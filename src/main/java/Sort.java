import java.io.*;

/**
 * Класс внешней сортировки слиянием без использования сторонних инструментов.
 * Сложность алгоритма равна сложности сортировки слиянием n*log(n).
 * <p>
 * Принцип:
 * 1) Исходный файл разбивается на два равных по 1 элемету;
 * 2) Проводится сортировка слиянием по 1 элементу и следует  запись в новый общий файл А.
 * 3) Файл А разбивается на два файла B и C по 2 элемента.
 * 4) Проводится сортировка слиянием по 2 элементам и следует запись в файл А.
 * 5) Файл А разбивается на два файла B и C по 4 элемента.
 * 6) Проводится сортировка слиянием по 4 элементам и следует запись в файл А.
 * 5) Файл А разбивается на два файла B и C по 8 элемента.
 * 6) Проводится сортировка слиянием по 8 элементов и следует запись в файл А.
 *
 * @author N.S.Kolesnik
 * @version 1.0
 */

public class Sort {
    /**
     * Класс разделения файла на два файла B и С.
     * @param fileName - обрабатываемыйй файл.
     * @param num - количество элеметов в итерации.
     * @param count - количество строк в обрабатываемом файле.
     */
    public static void preSort(String fileName, int num, int count) {
        try (BufferedReader readerA = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName)));
             FileWriter writerB = new FileWriter("fileB.txt", false);
             FileWriter writerC = new FileWriter("fileC.txt", false)) {
            for (int i = 0; i < count; ) {
                int j = 0;
                int k = 0;
                /** Записываем j строк в файл B */
                while (j < num) {
                    String strB = readerA.readLine();
                    if (strB == null) break;
                    writerB.write(strB + "\n");
                    j++;
                    i++;
                }
                /** Записываем k строк в файл C */
                while (k < num) {
                    String strC = readerA.readLine();
                    if (strC == null) break;
                    writerC.write(strC + "\n");
                    k++;
                    i++;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static int pow(int value, int powValue) {
        if (powValue == 1) {
            return value;
        } else {
            return value * pow(value, powValue - 1);
        }
    }
    /**
     * Сортировка
     */
    public static void sort(String fileName) {
        /** Вычисляем количество строк */
        int count = 0;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(fileName)))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        /** Делим на два файла, запись чередуем по 1 элементу */
        int num = 1;
        Sort.preSort(fileName, num, count);

        /** Первый пробег сортировки слиянием */
        Sort.sortProbeg(num, count);

        for(int g = 1; g < 20; g++){
            num = pow(2, g);
            Sort.preSort("fileA.txt", num, count);

            /** Четвертый пробег сортировки слиянием */
            Sort.sortProbeg(num, count);

        }
    }

    public static void sortProbeg(int num, int count) {
        try (BufferedReader readerB = new BufferedReader(
                new InputStreamReader(new FileInputStream("fileC.txt")));
             BufferedReader readerC = new BufferedReader(
                     new InputStreamReader(
                             new FileInputStream("fileB.txt")));
             FileWriter writerA = new FileWriter("fileA.txt", false)) {

            String strB = readerB.readLine();
            String strC = readerC.readLine();
            for (int i = 0; i < count; ) {
                int j = 0;

                /** Записываем строки в файл А */
                int b = 0;
                int c = 0;
                while (j < num * 2) // 1, 2, 4, 8
                {
                    if (strC == null && strB == null) {
                        i++;
                        break;
                    } else if (strC == null) {
                        writerA.write(strB + "\n");
                        b++;
                        strB = readerB.readLine();
                    } else if (strB == null) {
                        writerA.write(strC + "\n");
                        c++;
                        strC = readerC.readLine();
                    } else if (strB.compareToIgnoreCase(strC) < 0 && b < num) {
                        writerA.write(strB + "\n");
                        b++;
                        strB = readerB.readLine();
                    } else if (strB.compareToIgnoreCase(strC) < 0 && !(b < num)) {
                        writerA.write(strC + "\n");
                        c++;
                        strC = readerC.readLine();
                    } else if (strB.compareToIgnoreCase(strC) > 0 && c < num) {
                        writerA.write(strC + "\n");
                        c++;
                        strC = readerC.readLine();
                    } else if (strB.compareToIgnoreCase(strC) > 0 && !(c < num)) {
                        writerA.write(strB + "\n");
                        b++;
                        strB = readerB.readLine();
                    } else if (strB.compareToIgnoreCase(strC) == 0) {
                        writerA.write(strB + "\n");
                        writerA.write(strC + "\n");
                        b++;
                        c++;
                        strB = readerB.readLine();
                        strC = readerC.readLine();
                    }
                    j++;
                }
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }
}



