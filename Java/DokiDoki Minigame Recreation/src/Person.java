public class Person {
    private String Name;
    private String FileName;
    private int intScore = 0; 
    private String[] Words;

    // constructor method
    public Person (String strName, String strImgaeName) {
        this.Name = strName; 
        this.FileName = strImgaeName;
    }

    // set the word list
    public void setWords(String[] arrWords) {
        this.Words = arrWords;
    }

    // get the spesified amount of random words from the word list
    public String[] getWords (int amount) {
        String[] selected = new String[amount]; 
        int[] index = new int[amount];

        for (int i = 0; i < amount; i++) {
            int rand = (int)(Math.random() * (Words.length));
            selected[i] = Words[rand];
            index[i] = rand;
        }
        
        return selected;
    }

    public String getFileName() {
        return this.FileName;
    }

    public String getPersonName() {
        return this.Name;
    }

    public int getScore() {
        return this.intScore;
    }

    public void addToScore() {
        this.intScore += 1;
    }

    // character jump function for if the word if from their list
    public void jump(){

    }

}
