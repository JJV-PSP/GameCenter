/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dam.psp.GameCenter.Util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author pepea
 */
public class ImageTools {
    public static Image loadImgFromX64(String x64){
        if (x64 != null && !x64.isEmpty()) {
            try {
                String imageData = x64.split(",")[1];
                byte[] imageBytes = Base64.getDecoder().decode(imageData);
                ByteArrayInputStream byteArrayIS = new ByteArrayInputStream(imageBytes);
                BufferedImage bufferedImage = ImageIO.read(byteArrayIS);
                byteArrayIS.close();
                
                return SwingFXUtils.toFXImage(bufferedImage, null);
            } catch (IOException e) {
                System.err.println("Error in ImageTools loading Image from x64");
                System.err.println(e.getMessage());
            }
        } else {
            return new Image("/media/app_icon.png");
        }
        return null;
    }
    
    public static String loadX64FromImage(Image image) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } catch (IOException e) {
            System.err.println("Error in ImageTools loading x64 from Image");
            System.err.println(e.getMessage());
            return null;
        }
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);
        return "data:image/png;base64," + base64Image;
    }

    
    public static Image resizeImageToSquare(javafx.scene.image.Image originalImage) {
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(originalImage, null);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        int size = Math.min(width, height);
        BufferedImage squareImage = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = squareImage.createGraphics();
        int x = (width - size) / 2;
        int y = (height - size) / 2;
        g2d.drawImage(bufferedImage.getSubimage(x, y, size, size), 0, 0, null);
        g2d.dispose();
        return SwingFXUtils.toFXImage(squareImage, null);
    }
}
