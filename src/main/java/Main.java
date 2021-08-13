import java.util.Random;

/**
 * Основной рабочий класс с точкой входа.
 * @author N.S.Kolesnik
 * @version 1.0
 */

public class Main {

    public static void main(String[] args) {
        GenerateRandom.generateFile(10, 15);
        Sort.sort("testFile.txt");
    }
}
