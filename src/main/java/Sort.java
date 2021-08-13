import java.io.*;

public class Sort {
    public static void preSort(String fileName, int num, int count) {
        /** Разобьем файл на два равных */
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
        /** Удаляем файл А для очистки */
        File fileA = new File("fileA");
        fileA.delete();
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

        /** Первый пробег */
        Sort.sortProbeg("fileA.txt", num, count);

        /** Снова делим на два файла, запись чередуем по 2 элемента */
        num = 2;
        Sort.preSort("fileA.txt", num, count);

        /** Второй пробег */

        Sort.sortProbeg("fileA.txt", num, count);

        /** Снова делим на два файла, запись чередуем по 4 элемента */
        num = 4;
        Sort.preSort("fileA.txt", num, count);

        /** Третий пробег */

        Sort.sortProbeg("fileA.txt", num, count);

        /** Снова делим на два файла, запись чередуем по 8 элемента */
        num = 8;
        Sort.preSort("fileA.txt", num, count);

        /** Четвертый пробег */
        Sort.sortProbeg("fileA.txt", num, count);


    }

    public static void sortProbeg(String fileName, int num, int count) {
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

            /** Удаляем файлы B и C для очистки */
            File fileB = new File("fileB.txt");
            fileB.delete();
            File fileC = new File("fileB.txt");
            fileC.delete();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());

        }
    }
}



