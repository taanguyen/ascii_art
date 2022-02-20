import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Mapper {
    String ascii;

    public Mapper(String ascii) {
        this.ascii = ascii;
    }

    List<Character> mapBrightnessToAscii(ImageManipulation imageManipulation) {
        List<Integer> brightnessList = imageManipulation.brightnessMatrix();
        int maxBrightness = Collections.max(brightnessList);
        int minBrightness = Collections.min(brightnessList);

        List<Character> asciiMatrix = brightnessList.stream()
                .map(brightness -> {
                    double normalizedBrightness = 255. * (brightness - minBrightness) / (maxBrightness - minBrightness);
                    return convertToAscii(normalizedBrightness);
                }).collect(Collectors.toList());
        return asciiMatrix;
    }

    Character convertToAscii(double brightness) {

        // divide brightness by 255 to figure out how bright a pixel is relative to 0 - 255
        // multiply by the number of ascii characters possible
        // floor the result to get an integral index
        int numAscii = ascii.length() - 1;
        int index = (int) (brightness / 255 * numAscii);
        return ascii.charAt(index);
    }
}
