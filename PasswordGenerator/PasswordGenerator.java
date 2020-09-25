import java.util.*;

class PasswordGenerator {
    public static void main(String[] args) {
         while (true) {
            String[] choices = getChoices();
            int amount = Integer.parseInt(choices[0]);
            int counter = 0;

            while (counter < amount) {
                int maxLength = Integer.parseInt(choices[1]);
                int currentLength = 0;
                String password = "";

                while (currentLength < maxLength) {
                    password += getRandomCharacter(choices);
                    currentLength++;
                }
                System.out.println(password);
                counter++;
            }

            System.out.println("\nGenerate more passwords?");
            if ("no".equals(getYesOrNo())) {
                break;
            }
        }
    }

    static String[] getChoices() {
        String[] choices = new String[5];
        System.out.println("How many passwords should be generated?");
        choices[0] = String.valueOf(getInteger());

        System.out.println("Enter the password length (12-16 recommended)");
        choices[1] = String.valueOf(getLength());

        System.out.println("Add uppercase letters? (Recommended)\nYes / No");
        choices[2] = getAnswer("Uppercase letters");

        System.out.println("Add digits? (Recommended)\nYes / No");
        choices[3] = getAnswer("Digits");

        System.out.println("Add symbols (&, ?, @, /, =, $, and so on)? (Recommended)\nYes / No");
        choices[4] = getAnswer("Symbols");
        return choices;
    }

    static int getLength() {
        int length = getInteger();
        if (length < 12) {
            System.out.println("An at least 12-symbols password is safer");
            System.out.println("Enter a new password length?");

            if ("no".equals(getYesOrNo())) {
                return length;
            } else {
                System.out.println("Enter the length:");
                return getLength();
            }
        }
        return length;
    }

    static int getInteger() {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNextInt()) {
            int number = scan.nextInt();
            if (number <= 0) {
                System.out.println("The number should be more than zero");
                return getInteger();
            }
            return number;
        }
        System.out.println("You should enter an integer number");
        return getInteger();
    }

    static String getAnswer(String symbolType) {
        if ("yes".equals(getYesOrNo())) {
            return "true";
        }
        System.out.printf("%s make passwords safer\n", symbolType);
        System.out.println("Add them?");

        if ("yes".equals(getYesOrNo())) {
            return "true";
        }
        return "false";
    }

    static String getYesOrNo() {
        Scanner scan = new Scanner(System.in);
        if (scan.hasNext()) {
            String entered = scan.next().toLowerCase();
            if ("yes".equals(entered) || "no".equals(entered)) {
                return entered;
            }
        }
        System.out.println("You should enter \"yes\" or \"no\"");
        return getYesOrNo();
    }

    static char getRandomCharacter(String[] choices) {
        Random random = new Random();
        boolean addUppercase = Boolean.parseBoolean(choices[2]);
        boolean addDigits = Boolean.parseBoolean(choices[3]);
        boolean addSymbols = Boolean.parseBoolean(choices[4]);

        while (true) {
            int randomNumber = random.nextInt(100);
            if (randomNumber < 100 && randomNumber >= 75) {
                String lowercase = "abcdefghijklmnopqrstuvwxy";
                return lowercase.charAt(random.nextInt(lowercase.length()));
            } else if (addUppercase && randomNumber < 75 && randomNumber >= 50) {
                String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                return uppercase.charAt(random.nextInt(uppercase.length()));
            } else if (addDigits && randomNumber < 50 && randomNumber >= 25) {
                String digits = "0123456789";
                return digits.charAt(random.nextInt(digits.length()));
            } else if (addSymbols && randomNumber < 25 && randomNumber >= 0) {
                String symbols = "!#$()*+,-./:;<=>?@[]^_{|}~";
                return symbols.charAt(random.nextInt(symbols.length()));
            }
        }
    }
}