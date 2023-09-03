import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Window extends JFrame {
    public Window() {
        try {
            URL image = getClass().getClassLoader().getResource("resources/image.jpg");
            if (image != null) {
                BufferedImage originalImage = findNumber(ImageIO.read(image));
                int scaledWidth = originalImage.getWidth() / 4;  // קטן את הרוחב למחצית
                int scaledHeight = originalImage.getHeight() / 4; // קטן את הגובה למחצית
                Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

                JLabel label = new JLabel(new ImageIcon(scaledImage));
                this.add(label);
                this.setSize(scaledWidth, scaledHeight); // קבע את גודל החלון על פי התמונה המוקטנת
                this.setLocationRelativeTo(null);
                this.setResizable(false);
                this.setVisible(true);
            } else {
                System.out.println("Cannot find!");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //private BufferedImage findNumber(BufferedImage bufferedImage){
    //    BufferedImage processes = new BufferedImage(bufferedImage.getWidth(),
    //            bufferedImage.getHeight(),
    //            BufferedImage.TYPE_INT_RGB);
    //    for (int i = 0; i < bufferedImage.getWidth(); i++) {
    //        for (int j = 0; j < bufferedImage.getHeight(); j++) {
    //            int color = bufferedImage.getRGB(i,j);
    //            Color newColor = new Color(color);
    //            processes.setRGB(i,j,new Color(255-newColor.getRed(),
    //                    255-newColor.getGreen(),
    //                    255-newColor.getBlue()).getRGB());
    //        }
//
    //    }
    //    return processes;
    //}


    private BufferedImage findNumber(BufferedImage bufferedImage){
        BufferedImage processes = new BufferedImage(bufferedImage.getWidth(),
                bufferedImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                int color = bufferedImage.getRGB(i,j);
                Color newColor = new Color(color);
                if(isBlack(newColor)) {
                    processes.setRGB(i, j, new Color(newColor.getRed()+50,
                            newColor.getGreen()+50,
                            newColor.getBlue()+50).getRGB());
                }else{
                    processes.setRGB(i,j,newColor.getRGB());
                }
            }

        }
        return processes;
    }
    private boolean isBlack(Color color){
        return color.getBlue() +color.getRed() + color.getGreen() < 50;
    }
    private BufferedImage whiteToBlack(BufferedImage bufferedImage){
        BufferedImage newImage = new BufferedImage(bufferedImage.getWidth(),
                bufferedImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {
                int color = bufferedImage.getRGB(i,j);
                Color newColor = new Color(color);
                if(isWhite(newColor)){
                    newColor = new Color(0,0,0);
                    newImage.setRGB(i,j,newColor.getRGB());
                }else {
                    newImage.setRGB(i,j,newColor.getRGB());
                }
            }
        }
        return newImage;
    }

    private boolean isWhite(Color color){
        boolean result = false;
        if(color.getRed() > 200 && color.getGreen() > 200 && color.getBlue() > 200){
            result = true;
        }
        return result;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Window();
        });
    }
}