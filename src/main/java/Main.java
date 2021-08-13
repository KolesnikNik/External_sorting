
/**
 * Основной рабочий класс с точкой входа.
 *
 * @author N.S.Kolesnik
 * @version 1.0
 */

public class Main {

    public static void main(String[] args) {
        GenerateRandom.generateFile(1000, 15);
        Sort.sort("testFile.txt");
    }
}
