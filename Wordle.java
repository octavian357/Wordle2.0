import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.spec.GCMParameterSpec;

public class Wordle {
    private static String master = "";

    private String generateMaster(String file) throws IOException{
        File wordList = new File(file);
        //Scanner myScanner = new Scanner(wordList);
        int randNum = this.generateRandom(this.numLines(file));
        this.master = Files.readAllLines(Paths.get(file)).get(randNum);

        return master;
    }

    private int generateRandom(int max){
        Random rand = new Random();
        int randInt = rand.nextInt(max);
        return randInt;
    }

    public int numLines(String file){
        int numLines = 0;

        try (InputStream is = new BufferedInputStream(new FileInputStream(file))){
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean endsWithoutNewLine = false;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
                endsWithoutNewLine = (c[readChars - 1] != '\n');
            }
            if (endsWithoutNewLine) {
                ++count;
            }
            numLines = count;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numLines;
    }

    public void play(){

    }

    public static void main(String[] args) {
        boolean gameEnded = false;
        int guessCount = 0;

        Guess myGuess = new Guess();
        String file = "Words.txt";
        Wordle myGame = new Wordle();
        try {
            System.out.println(myGame.generateMaster(file));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Enter Your name: ");
        String user = in.nextLine();
        System.out.println("Hello " + user + "!! Welcome to WORDLE.");

        System.out.println("Correct letters int the RIGHT position will be " + Guess.ANSI_GREEN + "GREEN" + Guess.ANSI_RESET);
        System.out.println("Correct letters in the WRONG position will be " + Guess.ANSI_YELLOW + "YELLOW" + Guess.ANSI_RESET);

        while(gameEnded == false && guessCount != 6){
            System.out.println();
            System.out.println("Enter you guess:");
            String guess = in.nextLine();
            myGuess.setWord(guess);
            
            gameEnded = myGuess.tryGuess(master);
            myGuess.printGuess();
            myGuess.printColors();
            guessCount++;
            //System.out.println(guessCount);
        }
        if(gameEnded){
            System.out.println("CONGRADULATIONS!! YOU GOT THE ANSWER IN " + guessCount);
        }else{
            System.out.println("SORRY! YOU LOST. THE WORD IS " + master);
        }
        in.close();
    }
}
