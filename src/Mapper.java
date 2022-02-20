import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    String ascii;

    public Mapper(String ascii) {
        this.ascii = ascii;
    }

    List<Character> mapBrightnessToAscii(ImageManipulation imageManipulation) {
        List<Integer> brightnessMatrix = imageManipulation.brightnessMatrix();
        List<Character> asciiMatrix = brightnessMatrix.stream()
                .map(brightness -> normalizeBrightness(brightness)).collect(Collectors.toList());
        return asciiMatrix;
    }

    Character normalizeBrightness(int brightness) {
        // divide brightness by 255 to figure out how bright a pixel is relative to 0 - 255
        // multiply by the number of ascii characters possible
        // floor the result to get an integral index
        int numAscii = ascii.length();
        int index = (int) Math.floor(brightness / 255. * numAscii);
        return ascii.charAt(index);
    }
}
