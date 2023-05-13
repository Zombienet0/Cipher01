import java.io.*;
import java.util.Scanner;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.lang.System.out;

/*
* 1) создайте 2 файла: test.txt и test1.txt
* 2) в test.txt вставьте тот текст, который вы хотите зашифровать или разшифровать ключом или BruteForce
* 3) запустите программу и следуйте ее указаниям
* 4) результат программы будет лежать в test1.txt
* 5) если будут вопросы пишите на cooldima641@gmail.com или в telegram https://t.me/Zombinet0 я быстро вам отвечу и объясню по коду если нужно
* 6) знаю, что код не супер и возможно выглядит как свалка, но я очень старался)))
* */

class Main {

    private static final String alphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!? ";
    private static final String reAlphabet = " ?!-:\",.яюэьыъщшчцхфутсрпонмлкйизжёедгвба";
    private static final String[] words = {"робот", "работа", "сомне", "желание", "ребенок", "никуда", "элемент", "соврем", "голова", "равно", "исслед", "паста", "почему", "процесс", "самый", "сегодня", "стать", "большой", "интерес", "другой", "место", "пример", "источник", "горовор", "оказаться", "источн", "программа", "наблюд", "сделать", "сказа", "чтобы", "смотреть", "пользовател", "новый", "проект", "жизнь", "первый", "день", "спрос", "хотеть", "ничто", "потом", "очень", "вместе", "хотеть", "почти", "существ", "голова", "надо", "хорошо", "видеть", "идти", "теперь", "тоже", "стоять", "сторон", "главный", "сейчас", "можно", "после", "слово", "здесь", "думать"};


    private static char symbol_right_shift(char symbol, int shift) {
        if (alphabet.indexOf(symbol) != -1) {
            return alphabet.charAt((alphabet.indexOf(symbol) + shift) % alphabet.length());
        } else {
            return symbol;
        }
    }

    private static char symbol_left_shift(char symbol, int shift) {
        if (reAlphabet.indexOf(symbol) != -1) {
            return reAlphabet.charAt((reAlphabet.indexOf(symbol) + shift) % reAlphabet.length());
        } else {
            return symbol;
        }
    }

    public static void main(String[] args) {
        String filePath = "D:\\test.txt";
        String content = null;
        try {
            content = readFile(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.println(content);
        out.println("Выберите действие: 1) Шифр 2) Дешифр 3) Brute Force");
        Scanner console = new Scanner(System.in);
        int choise = console.nextInt();
        console.nextLine();
        if ((choise == 1) || (choise == 2)) {
            out.println("Смещение 1) Вправо  2) Влево");
            int choiseSide = console.nextInt();
            console.nextLine();
            if (choise == 1) {
                out.println("Насколько символов вы хотите сместить?: ");
                int keyCode0 = Integer.parseInt(console.nextLine());
                int keyCode = keyCode0 + 1;
                Code(content, keyCode, choiseSide);
            } else {
                out.println("Введите ключ: ");
                int keyCode0 = Integer.parseInt(console.nextLine());
                int keyCode = keyCode0 + 1;
                DeCode(content, keyCode, choiseSide);
            }
        } else {
            BruteForce(content);

        }
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        return Files.readString(Paths.get(path), encoding);
    }

    private static void Code1(String content, int keyCode) {
        int kc = keyCode - 1;
        File file = new File("D:\\test1.txt");
        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bf = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bf)) {
            content = content.toLowerCase();
            for (int i = kc; i < keyCode; i++) {
                for (int j = 0; j < content.length(); j++) {
                    out.print(symbol_right_shift(content.charAt(j), i));
                }
            }
            System.out.println("Ваш текст готов!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void Code2(String content, int keyCode) {

        int kc = keyCode - 1;
        File file = new File("D:\\test1.txt");

        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bf = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bf)) {
            content = content.toLowerCase();
            for (int i = kc; i < keyCode; i++) {
                for (int j = 0; j < content.length(); j++) {
                    out.print(symbol_left_shift(content.charAt(j), i));
                }
            }
            System.out.println("Ваш текст готов!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void Code3(String content, int keyCode) {
        int kc = keyCode - 1;
        File file = new File("D:\\test1.txt");
        try (FileWriter fw = new FileWriter(file);
             BufferedWriter bf = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bf)) {
            content = content.toLowerCase();
            for (int i = kc; i < keyCode; i++) {
                for (int j = 0; j < content.length(); j++) {
                    out.print(symbol_right_shift(content.charAt(j), i));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filePath = "D:\\test1.txt";

        try {
            content = readFile(filePath, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BruteForce(content);
    }

    private static void Code(String content, int keyCode, int choiseSide) {
        content = content.toLowerCase();
        if (choiseSide == 1) {
            Code1(content, keyCode);
        } else {
            Code2(content, keyCode);
        }

    }

    private static void DeCode(String content, int keyCode, int choiseSide) {
        content = content.toLowerCase();
        if (choiseSide == 1) {
            Code1(content, keyCode);
        } else {
            Code2(content, keyCode);
        }
    }

    private static void BruteForce(String content) {
        boolean b = false;
        int c = -1;
        do {
            c++;
            b = content.contains(words[c]);
        } while ((b != true) && (c < 62));
        if (c == 62) {
            Code3(content, 2);
        } else {
            Code1(content, 1);
        }
    }
}