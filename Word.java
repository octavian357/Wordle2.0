public class Word {
    //public LinkedList<Character> lWrod = new LinkedList<>();
    public String word;
    public int size;

    public Word(){
        this.word = "BRIAN";
        this.size = word.length();
    }

    public Word(String str){
        this.word = str.toUpperCase();
        this.size = word.length();
    }

    public int getSize(){
        return this.size;
    }

    public void printWord(){
        System.out.println(this.word);
    }

    public static void main(String[] args) {
        Word myWord = new Word("CHEEK");
        myWord.printWord();
        System.out.println(myWord.getSize());
    }
}
