/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package secondedition;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author christine
 */
public class ImageProcessor implements Runnable {
    private String file_path;
    private boolean flag_stop = true;
    
    public void run(){
        System.out.println("Screen Shot Begin");
        while (flag_stop) {            
            try{
                file_path = getDateTime();
                screenshot(file_path);
//                imageScaling();
                Thread.sleep(10000);
            } catch (InterruptedException iex) {
                System.out.println("Exception in thread: "+iex.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Screen Shot Stop");
    }
    
    public void screenshot(String fileName) throws Exception {
        Robot robot = new Robot();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle rect = new Rectangle(0, 0, d.width, d.height);
        BufferedImage image = robot.createScreenCapture(rect);
        file_path = fileName + "pic";
        ImageIO.write(image, "jpg", new File(file_path));
    }
    
    public void readImage(String path) throws IOException {
        File sourceimage = new File(path);
        Image image = ImageIO.read(sourceimage);
        
        JFrame frame = new JFrame();
        frame.setSize(1200, 800);
        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);
    }
    
    public void imageScaling() {
        try {
            File fi = new File(file_path); //大圖文件 
            File fo = new File(file_path + ".jpg"); //將要轉換出的小圖文件 
            int nw = 100;
            AffineTransform transform = new AffineTransform();
            BufferedImage bis = ImageIO.read(fi);
            int w = bis.getWidth();
            int h = bis.getHeight();
            double scale = (double) w / h;
            int nh = (nw * h) / w;
            double sx = (double) nw / w;
            double sy = (double) nh / h;
            transform.setToScale(sx, sy);
            System.out.println(w + " " + h);
            AffineTransformOp ato = new AffineTransformOp(transform, null);
            BufferedImage bid = new BufferedImage(nw, nh, BufferedImage.TYPE_3BYTE_BGR);
            ato.filter(bis, bid);
            ImageIO.write(bid, "jpeg", fo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getDateTime(){
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy_MM_dd hh_mm_ss");
        Date date = new Date();
        String strDate = sdFormat.format(date);
        //System.out.println(strDate);
        return strDate;
    }
    
    public String getFileName() {
        return file_path;
    }
    
    public void stop() {
        flag_stop = false;
    }
}
