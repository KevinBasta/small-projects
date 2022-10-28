import java.util.ArrayList;

public class Main {
    private static ArrayList<Person> peopleList = new ArrayList<Person>();
    private static int bigArrayLength = 12;
    private static int smallArrayLength = 4;

    public static void main() {
        String[] HappyList = {"chocolate", "happy", "destiny", "raibow", "fantacy", "energy", "stars", "moonlight", "friends", "sun", "moon", "cookies"};
        String[] DeepList = {"deep", "dark fantacy", "poetic", "hidden", "meaning", "extreme", "emotion", "life", "weight", "words", "feeling", "desire"};
        String[] SadList = {"sad", "lost", "bored", "friendless", "alone", "solidarity", "cry", "hurt", "fall", "anxiety", "wolf", "blue", "whatislife"};
        
        Person Yuri = new Person("Yuri", "YuriPixelArt.png");
        Yuri.setWords(DeepList);
        peopleList.add(Yuri);

        Person Monika = new Person ("Monika", "MonikaPixelArt.png");
        Monika.setWords(HappyList);
        peopleList.add(Monika);

        Person Sayori = new Person("Sayori", "SayoriPixelArt.png"); 
        Sayori.setWords(SadList);
        peopleList.add(Sayori);
    }

    public static String[][] newWordsArray() {
        String[] YuriGet = peopleList.get(0).getWords(smallArrayLength);
        String[] MonikaGet = peopleList.get(1).getWords(smallArrayLength);
        String[] SayoriGet = peopleList.get(2).getWords(smallArrayLength);
        String[][] DisplayList = new String[2][bigArrayLength];

        DisplayList = shuffleArray(DisplayList, YuriGet, "Y");
        DisplayList = shuffleArray(DisplayList, MonikaGet, "M");
        DisplayList = shuffleArray(DisplayList, SayoriGet, "S");
        printList(DisplayList);

        return(DisplayList);
    }

    public static String[][] shuffleArray(String[][] fullArray, String[] smallArray, String strId) {
        
        for (int i = 0; i < smallArrayLength; i++) {
            int randBig = (int)(Math.random() * (bigArrayLength));

            if (fullArray[1][randBig] == null) {
                fullArray[1][randBig] = smallArray[i];
                fullArray[0][randBig] = strId;
            } else {
                randBig = (int)(Math.random() * (bigArrayLength));
                i -= 1;
            }
        }

        return fullArray;
    }

    public static Person accessPerson(int indexNumber) {
        return peopleList.get(indexNumber);
    }

    public static void printList(String[][] fullArray) {
        System.out.println("     ");
        for (int i = 0; i < 12; i++) {
            System.out.print(fullArray[0][i] + " ");
            System.out.println(fullArray[1][i]);
        }
        System.out.println("     ");
    }
}
