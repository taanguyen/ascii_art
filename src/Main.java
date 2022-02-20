import java.io.IOException;
import java.util.List;

public class Main {
    public static void main (String[] args) throws IOException {
        String path = "imgs/bts_v.jpeg";
        ImageManipulation imageManipulation = new ImageManipulation(path);
        imageManipulation.savePixelsToFile("bts.txt");
        int height = imageManipulation.getHeight();
        int width = imageManipulation.getWidth();

        Mapper mapper = new Mapper("`^\",:;Il!i~+_-?][}{1)(|\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$");
        List<Character> asciiChars = mapper.mapBrightnessToAscii(imageManipulation);

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                System.out.print(asciiChars.get(h * width + w));
            }
            System.out.println();
        }

    }
}
