package net.remgant.graphics;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ChernoffFaceTest {
    @Test
    public void testHead() throws Exception {

        for (int i = 0; i < 6; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            double d = (double) i / (double) 6;
            ChernoffFace.create(graphics, d, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5);
            ImageIO.write(image, "PNG", new File(String.format("./head-%3.2f.png", d)));
        }
    }

    @Test
    public void testEyeShape() throws Exception {

        for (int i = 0; i < 6; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            double d = (double) i / (double) 6;
            ChernoffFace.create(graphics, 0.5, d, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5);
            ImageIO.write(image, "PNG", new File(String.format("./eye-shape-%3.2f.png", d)));
        }
    }

    @Test
    public void testEyeSpacing() throws Exception {
        for (int i = 0; i < 6; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            double d = (double) i / (double) 6;
            ChernoffFace.create(graphics, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, d, 0.5, 0.5, 0.5);
            ImageIO.write(image, "PNG", new File(String.format("./eye-spacing-%3.2f.png", d)));
        }
    }

    @Test
    public void testEyeSize() throws Exception {
        for (int i = 0; i < 6; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            double d = (double) i / (double) 6;
            ChernoffFace.create(graphics, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, d, 0.5, 0.5);
            ImageIO.write(image, "PNG", new File(String.format("./eye-size-%3.2f.png", d)));
        }
    }
}
