import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class BufferedImageResizeExample extends JFrame {
    public BufferedImageResizeExample() {
        setTitle("BufferedImage Resize Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);

        // Load your image as a BufferedImage
        try {
            URL url = getClass().getClassLoader().getResource("resources/image.jpg");
            if (url != null) {
//                BufferedImage originalImage = contrast(ImageIO.read(url), 100);
                BufferedImage originalImage = findNumber(ImageIO.read(url));
//                BufferedImage originalImage = ImageIO.read(url);
                int newWidth = 800;
                int newHeight = 600;

                // Create a new BufferedImage with the resized dimensions
                BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, originalImage.getType());


//                 Scale the original image to the new dimensions
                Graphics2D g2d = resizedImage.createGraphics();
                g2d.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
                g2d.dispose();

                System.out.println(resizedImage.getWidth());

                // Display the resized BufferedImage
                JLabel imageLabel = new JLabel(new ImageIcon(resizedImage));
                getContentPane().add(imageLabel);


                pack();
                setVisible(true);
            } else {
                System.out.println("not found");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
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

    private BufferedImage contrast (BufferedImage original, int by) {
        BufferedImage processed = new BufferedImage(
                original.getWidth(),
                original.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < original.getHeight(); i++) {
            for (int j = 0; j < original.getWidth(); j++) {
                Color color = new Color(original.getRGB(j, i));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                Color newColor = new Color(intensify(red, by), intensify(green, by), intensify(blue, by));
                processed.setRGB(j, i, newColor.getRGB());
            }
        }
        return processed;

    }
    private int intensify(int original, int by) {
        if (original > 128) {
            original *= ((100 + by) / 100);
        } else {
            original *= ((100 - by) / 100);
        }
        return Math.min(255, original);

    }
    private BufferedImage grayScale (BufferedImage original) {
        BufferedImage processed = new BufferedImage(
                original.getWidth(),
                original.getHeight(),
                BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < original.getHeight(); i++) {
            for (int j = 0; j < original.getWidth(); j++) {
                Color color = new Color(original.getRGB(j, i));
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();
                int average = (red + green + blue) / 3;
                Color newColor = new Color(average, average, average);
                processed.setRGB(j, i, newColor.getRGB());
            }
        }
        return processed;
    }
    public static void main(String[] args)  {

        SwingUtilities.invokeLater(() -> {
            new BufferedImageResizeExample();
        });
//        ApiUtil.submit("95628161",95);
    }
}
