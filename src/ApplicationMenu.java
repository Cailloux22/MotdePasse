import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class ApplicationMenu {

    public static final Scanner SCANNER = new Scanner(System.in);
    private static final String FILENAME="OOO.txt";

    public static void contextMenu(){
        boolean running=true;
        while (running){
            boolean badAnswer=true;
            while(badAnswer) {
                System.out.println("1 - créer un nouveaux mot de passe");
                System.out.println("2 - récupérer mes mots de passe");
                System.out.println("3 - quitter");
                try {


                    int rep = Integer.parseInt(SCANNER.nextLine());
                    switch (rep) {
                        case 1:
                            generatePassword();
                            badAnswer = false;
                            break;
                        case 2:
                            try (FileReader fr = new FileReader(FILENAME))
                            {
                                int content;
                                while ((content = fr.read()) != -1) {
                                    System.out.print((char) content);
                                }
                                System.out.println("\n");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            badAnswer = false;
                            break;
                        case 3:
                            badAnswer = false;
                            running = false;
                            System.out.println("STOP");
                            break;
                        default:
                            System.out.println(rep);
                            break;
                    }
                }catch (Exception e){
                    System.out.println("Veuillez saisir un chiffre valide");
                }
            }
        }
    }

    public static void generatePassword(){

        Random random = new Random();
        String alphabet = "&é(-è_çà)=$*ù^!mp:loik;,juyhnbgtrfvcdezsxwqa1234567890°+£¨%µ§/MPOL.KKIUJ?NHYTGBVFRECCXDSZAQW²'";
        int position = random.nextInt(alphabet.length());
        int repeat = random.nextInt(12)+12;

        StringBuilder bigMdpStrong= new StringBuilder();

        for (int i=0;i<repeat;i++){
            bigMdpStrong.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }

        System.out.println("mot de passe générer : "+bigMdpStrong);
        boolean badAnswer= true;
        while(badAnswer) {
            try {
                System.out.println("Souhaitez vous générer un nouveau mot de passe O/N");
                String rep=SCANNER.nextLine();
                switch (rep) {
                    case "O":

                        generatePassword();
                        badAnswer = false;
                        break;
                    case "N":
                        storePassword(bigMdpStrong.toString());
                        badAnswer = false;
                        break;

                    default:
                        break;
                }
            }catch (Exception e ){
                System.out.println("Veuillez saisir O ou N");
            }
        }
    }

    public static void storePassword(String mdp){
            boolean badAnswer= true;
            while(badAnswer) {
                try {
                    System.out.println("Voulez vous stockez ce mot de passe O/N");
                    String rep=SCANNER.nextLine();
                    switch (rep) {
                        case "O":
                            store(mdp);
                            badAnswer = false;
                            break;
                        case "N":
                            badAnswer = false;
                            break;

                        default:
                            break;
                    }
                }catch (Exception e ){
                    System.out.println("Veuillez saisir O ou N");
                }
            }

    }
    public static void store(String mdp){
        try{
        // Recevoir le fichier


        File f = new File(FILENAME);
        FileWriter fstream = new FileWriter(FILENAME, true);
        BufferedWriter out = new BufferedWriter(fstream);

        // Créer un nouveau fichier
        // Vérifier s'il n'existe pas
        if (f.createNewFile()){
            System.out.println("Mot de passe enregistrer");
            //Close the output stream
            out.write(mdp);
            out.close();
        }
        else {


            out.newLine();
            out.write(mdp);
            out.close();
            System.out.println("Mot de passe enregistré");
        }
    }
        catch (Exception e) {
        System.err.println(e.getMessage());
    }
    }


}
