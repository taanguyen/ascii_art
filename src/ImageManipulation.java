import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ImageManipulation {
    BufferedImage bufferedImage;
    int[][] pixels;

    public ImageManipulation(String path) {
        try {
            this.bufferedImage = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pixels = getPixelMatrix();
    }

    public ImageManipulation(String path, int scale) {
        try {
            this.bufferedImage = ImageIO.read(new File(path));
            resizeImage(scale);
        } catch (IOException e) {
            e.printStackTrace();
        }

        pixels = getPixelMatrix();
    }

    int getHeight() {
        return bufferedImage.getHeight();
    }

    int getWidth() {
        return bufferedImage.getWidth();
    }

    int[][] getPixelMatrix() {
        int height = getHeight();
        int width = getWidth();
        int[][] pixels = new int[height*width][3];
        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                int pixel = bufferedImage.getRGB(w, h);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;
                pixels[h * width + w] = new int[]{red, green, blue};
            }
        }
        return pixels;
    }

    void savePixelsToFile(String filename) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(filename);
            fw.write("height: " + getHeight() + "\n");
            fw.write("width: " + getWidth() + "\n");
            fw.write("num pixels: " + pixels.length + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int[] pixel : pixels){
            String[] strArray = Arrays.stream(pixel)
                    .mapToObj(String::valueOf)
                    .toArray(String[]::new);
            System.out.println(String.join(",", strArray));
            try {
                fw.write( String.join(",", strArray) + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    List<Integer> brightnessMatrix() {
        return Arrays.stream(pixels)
                .map(pixel -> (pixel[0] + pixel[1] + pixel[2])/ 3).collect(Collectors.toList());
    }

    void resizeImage(int scale) {
        int width = getWidth() / scale;
        int height = getHeight() / scale;
        Image resizedImage = bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage outputImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        outputImage.createGraphics().drawImage(resizedImage, 0, 0, null);
        bufferedImage = outputImage;
    }

}
