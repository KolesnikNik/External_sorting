import java.io.*;
import java.util.Random;

/**
 * Класс создающий файл со случайными строками заданной длины.
 * @author N.S.Kolesnik
 * @version 1.0
 */
public class GenerateRandom {
    /**
     * Метод, генерирующий случайную строку заданной длины.
     * @param length - длина строки.
     */

    public static String generateString(int length) {
        final char[] pool = {
                'a', 'b', 'c', 'd', 'e', 'f', 'g',
                'h', 'i', 'j', 'k', 'l', 'm', 'n',
                'o', 'p', 'q', 'r', 's', 't', 'u',
                'v', 'w', 'x', 'y', 'z'};

        Random rnd = new Random();


        char[] text = new char[length];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(pool[rnd.nextInt(pool.length)]);
        }

        return new String(sb);
    }
    /**
     * Метод, генерирующий файл с заданным количеством строк.
     * @param count - количество строк.
     * @param length - длина строки.
     */
    public static void generateFile(int count, int length) {
        try (
                FileWriter writer = new FileWriter("testFile.txt", false)) {
            for (int i = 0; i < count; i++) {

                writer.write(generateString(length) + "\n");
            }

        } catch (
                IOException ex) {
            System.out.println(ex.getMessage());

        }
    }

}
