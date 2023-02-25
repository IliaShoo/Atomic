import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static AtomicInteger THREE_WORD = new AtomicInteger();
    public static AtomicInteger FOUR_WORD = new AtomicInteger();
    public static AtomicInteger FIVE_WORD = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Runnable FirstExamination = () -> {
            for (String word :texts) {
                StringBuilder strBuilder = new StringBuilder(word);
                strBuilder.reverse();
                String invertedText = strBuilder.toString();

                if(word.equalsIgnoreCase(invertedText)){
                        if (word.length() == 3) {
                            THREE_WORD.getAndIncrement();
                        } else if (word.length() == 4) {
                            FOUR_WORD.getAndIncrement();
                        } else if(word.length() ==5){
                            FIVE_WORD.getAndIncrement();
                        }
                }
            }
        };

        Runnable SecondExamination = () -> {
            for (String word: texts) {
                char letter = word.charAt(0);
                boolean isTrue = true;
                for (int j = 0; j < word.length(); j++) {
                    if (word.charAt(j) != letter) {
                        isTrue = false;
                    }
                }
                if (isTrue) {
                    if (word.length() == 3) {
                        THREE_WORD.getAndIncrement();
                    } else if (word.length() == 4) {
                        FOUR_WORD.getAndIncrement();
                    } else if (word.length() == 5) {
                        FIVE_WORD.getAndIncrement();
                    }
                }
            }


        };

        Runnable ThirdExamination = () -> {
            for (String word: texts) {
                char[] letters = word.toCharArray();
                int[] unicodes = new int[letters.length];
                for (int j = 0; j < unicodes.length; j++) {
                    unicodes[j] = letters[j];
                }
                boolean isTrue = true;
                for (int a = 0; a < unicodes.length - 1; a++) {
                    if (unicodes[a] > unicodes[a + 1]) {
                        isTrue = false;
                    }
                }
                 if(isTrue){
                        if (word.length() == 3) {
                            THREE_WORD.getAndIncrement();
                        } else if (word.length() == 4) {
                            FOUR_WORD.getAndIncrement();
                        } else if(word.length() ==5){
                            FIVE_WORD.getAndIncrement();
                        }
                    }
                }
        };
        Thread firstThread = new Thread(FirstExamination);
        Thread secondThread = new Thread(SecondExamination);
        Thread thirdThread = new Thread(ThirdExamination);
        firstThread.start();
        secondThread.start();
        thirdThread.start();
        firstThread.join();
        secondThread.join();
        thirdThread.join();

        System.out.println("Красивых слов с длиной 3 -> " + THREE_WORD + "\n" +
                "Красивых слов с длиной 4 -> " + FOUR_WORD + "\n" +
                "Красивых слов с длиной 5 -> " + FIVE_WORD);

    }

    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}
