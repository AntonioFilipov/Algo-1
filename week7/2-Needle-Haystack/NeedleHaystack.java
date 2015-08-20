import java.math.BigInteger;
import java.util.Scanner;

public class NeedleHaystack {

    private static StringBuilder result = new StringBuilder();

    private static final String BASE = "101";

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String haystack = s.next();
        String needle = s.next();

        rabinKarp(haystack, needle);

        System.out.println(result.toString());
    }

    private static void rabinKarp(String haystack, String needle) {
        int needleLenght = needle.length();
        int haystackLenght = haystack.length();

        BigInteger hashHaystack = getHash(haystack.substring(0, needleLenght), new BigInteger("0"), 0, needleLenght);
        BigInteger hashNeedle = getHash(needle, new BigInteger("0"), 0, needleLenght);

        for (int i = 0; i <= haystackLenght - needleLenght; i++) {

            if (hashHaystack.equals(hashNeedle)) {
                if (haystack.substring(i, i + needleLenght).equals(needle)) {
                    result.append(i + "\n");
                }
            }

            // The end of possible substrings
            if (i == haystackLenght - needleLenght) {
                break;
            }

            hashHaystack = getHash(haystack, hashHaystack, i + 1, needleLenght + i + 1);
        }
    }

    private static BigInteger getHash(String needle, BigInteger prevHash, int startIndex, int endIndex) {
        String needleInRange = needle.substring(startIndex, endIndex);

        BigInteger hash = new BigInteger("0");
        int size = needleInRange.length();

        if (startIndex == 0) {
            for (int i = 0; i < needleInRange.length(); i++) {

                BigInteger currentBasePower = new BigInteger(BASE).pow(size - 1);
                BigInteger currentStep = new BigInteger(getAsciiCode(needleInRange.charAt(i)));

                hash = hash.add(currentStep.multiply(currentBasePower));

                size--;
            }
        } else {

            BigInteger prevLetterPower = new BigInteger(BASE).pow(size - 1);
            BigInteger prevLetter = new BigInteger(getAsciiCode(needle.charAt(startIndex - 1)));

            prevHash = ((prevHash.subtract(prevLetterPower.multiply(prevLetter))).multiply(new BigInteger(BASE)))
                    .add(new BigInteger(getAsciiCode(needle.charAt(endIndex - 1))));

            hash = prevHash;
        }

        return hash;
    }

    private static String getAsciiCode(char c) {
        int value = (int) c;
        return String.valueOf(value);
    }
}