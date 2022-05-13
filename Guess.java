import java.util.LinkedList;


public class Guess{
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public LinkedList<String> colors = new LinkedList<>();
    public String word;
    public int size;

    public Guess(){
        this.word = "BRIAN";
        this.size = word.length();
        
    }

    public Guess(String str){
        setWord(str);
    }

    public void setWord(String str){
        this.word = str.toUpperCase();
        this.size = str.length();
        colors.clear();
        for(int i = 0; i < this.size; i++){
            colors.add("RESET");
        }
        //this.printColors();
    }

    public void printColors(){
        //String[] colorArray = colors.toArray(new String[0]);
        System.out.println(colors.toString());
    }

    public boolean tryGuess(String master){
        //populate colors

        //this.printColors();

        //check if word
        if(this.word.equals(master)){
            colors.clear();
            for(int i = 0; i < this.size; i++){
                colors.add("GREEN");
            }
            return true;
        }

        //pass twice for greens
        checkGREEN(master);

        //pass once for yellows
        checkYELLOW(master);

        return false;
    }

    private void checkYELLOW(String master){
        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                Character c = this.word.charAt(i);
                countColorLet(c);
                if(master.contains(c.toString()) && this.colors.get(i) != "GREEN" && countColorLet(c) < countLet(master, c)){
                    colors.remove(i);
                    colors.add(i, "YELLOW");
                }
            }
        }
    }

    private void checkGREEN(String master){
        for(int i = 0; i < this.size; i++){
            //check for right letter in right spot
            if(this.word.charAt(i) == master.charAt(i)){
                colors.remove(i);
                colors.add(i, "GREEN");
            }
        }
    }

    private int countColorLet(char c){
        int num = 0;
        for(int i = 0; i < this.size; i++){
            if(this.word.charAt(i) == c && this.colors.get(i) != "RESET"){
                num++;
            }
        }
        return num;
    }

    private int countLet(String master, char c){
        int num = 0;
        for(int i = 0; i < this.size; i++){
            if(master.charAt(i) == c){
                num++;
            }
        }
        return num;
    }

    public void printGuess(){
        for(int i = 0; i < this.size; i++){
            if(colors.get(i) == "GREEN"){
                System.out.print(ANSI_GREEN + this.word.charAt(i) + ANSI_RESET);
            }else if(colors.get(i) == "YELLOW"){
                System.out.print(ANSI_YELLOW + this.word.charAt(i) + ANSI_RESET);
            }else{
                System.out.print(this.word.charAt(i));
            }
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        Guess myGuess = new Guess("PNAAN");
        myGuess.tryGuess("BRIAN");
        
        myGuess.printGuess();
        myGuess.printColors();
    }
}
