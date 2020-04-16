/*
 *
 *********************** Digital Text To Image*********************************
 *
 * Author: Aankik Koshti
 * github: cortex790
 *
 * 64 Words per line
 * only small letters allowed
 *
 *
 *
 *
 * */
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageTweak {

    public static int counter=0;
    public static void main(String[] args) throws IOException {

        try(BufferedReader l=new BufferedReader(new FileReader("loc.txt"))) {
            String s;
            while((s=l.readLine() )!=null) {
                SingleLineConverter(s, s.length());

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(counter>0){
            MultipleLineConverter();
        }
    }



    public static void SingleLineConverter(String x, int len) throws IOException {
        counter++;

        File[] imgFiles = new File[len];
        for (int i = 0; i < len; i++) {
            if(x.charAt(i)>='a' && x.charAt(i)<='z')
                imgFiles[i] = new File("fonts/"+x.charAt(i)+".jpg");
            else
                imgFiles[i] = new File("fonts/space.jpg");
        }

        //creating a bufferd image array from image files
        BufferedImage[] buffImages = new BufferedImage[len];
        for (int i = 0; i < len; i++) {
            buffImages[i] = ImageIO.read(imgFiles[i]);
        }

//        int width = 0;
//        for (int i = 0; i <len ; i++) {
//            width+=buffImages[i].getWidth();
//        }
        int height = buffImages[0].getHeight() ;
        BufferedImage image = new BufferedImage(1500,height,   buffImages[0].getType());
        Graphics2D g = image.createGraphics();
        g.setColor(Color.white);
        g.fillRect ( 0, 0,1500, height);


        int widthCurr=0;
        for (int i = 0; i < len; i++) {
            g.drawImage(buffImages[i], widthCurr, 0, null);
            widthCurr += buffImages[i].getWidth();

            System.out.println("SingleLineConverter: "+counter);
        }

        g.dispose();
        ImageIO.write(image, "jpeg", new File("SingleLineText/"+counter+".jpeg"));


    }



    private static void MultipleLineConverter() throws IOException {
        File[] imgFiles = new File[counter];
        for (int i = 0; i < counter; i++) {

            imgFiles[i] = new File("SingleLineText/"+(i+1)+".jpeg");


        }

        //creating a bufferd image array from image files
        BufferedImage[] buffImages = new BufferedImage[counter];
        for (int i = 0; i < counter; i++) {
            buffImages[i] = ImageIO.read(imgFiles[i]);
        }

//
//        int width = 0;
//        int height =0;
//        for (int i = 0; i <counter ; i++) {
//            width=Math.max(buffImages[i].getWidth(),width);
//            height+=buffImages[i].getHeight();
//        }

        BufferedImage image = new BufferedImage(1500,1900,   buffImages[0].getType());
        Graphics2D g = image.createGraphics();
        g.setColor(Color.white);
        g.fillRect ( 0, 0,1500, 1900);

        int heightCurr=0;
        for (int i = 0; i < counter; i++) {

            g.drawImage(buffImages[i], 0, heightCurr, null);
            heightCurr += buffImages[i].getHeight();

            System.out.println("MultipleLineConverter: "+i+1);

        }


        g.dispose();
        ImageIO.write(image, "jpeg", new File("FinalImage/final.jpeg"));

    }



}

