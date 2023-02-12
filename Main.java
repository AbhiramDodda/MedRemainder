import java.io.File;
import java.io.*;
import java.util.*;
import javax.sound.sampled.*;
public class Main {
    
     public static void main(String args[])throws UnsupportedAudioFileException, IOException,LineUnavailableException
     {  Timer timer=new Timer();

        Scanner scan=new Scanner (System.in);
        File file=new File("Sound1.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        String response="";
        while(!response.equals("C"))
        {
            System.out.println("P_PLAY S_STOP C_CLOSE");
            System.out.print("Enter :");
            response = scan.next();
            response = response.toUpperCase();
            switch(response){
                case("P"):clip.start();break;
                case("S"):clip.stop();break;
                 case("C"):clip.close();break;
                default:System.out.println("ENter a valid key");
            }

        }
        
     }
    
}
