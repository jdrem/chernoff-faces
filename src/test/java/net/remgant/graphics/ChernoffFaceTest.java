package net.remgant.graphics;

import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class ChernoffFaceTest {
    @Test
    public void testHead() throws Exception {

        for (int i = 0; i <= 6; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            double d = (double) i / (double) 6;
            ChernoffFace.create(graphics, d, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5);
            ImageIO.write(image, "PNG", new File(String.format("./head-%3.2f.png", d)));
        }
    }

    @Test
    public void testEyeShape() throws Exception {

        for (int i = 0; i <= 6; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            double d = (double) i / (double) 6;
            ChernoffFace.create(graphics, 0.5, d, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5);
            ImageIO.write(image, "PNG", new File(String.format("./eye-shape-%3.2f.png", d)));
        }
    }

    @Test
    public void testEyeSpacing() throws Exception {
        for (int i = 0; i <= 6; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            double d = (double) i / (double) 6;
            ChernoffFace.create(graphics, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, d, 0.5, 0.5, 0.5);
            ImageIO.write(image, "PNG", new File(String.format("./eye-spacing-%3.2f.png", d)));
        }
    }

    @Test
    public void testEyeSize() throws Exception {
        for (int i = 0; i <= 6; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            double d = (double) i / (double) 6;
            ChernoffFace.create(graphics, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, d, 0.5, 0.5);
            ImageIO.write(image, "PNG", new File(String.format("./eye-size-%3.2f.png", d)));
        }
    }

    @Test
    public void testPupilSize() throws Exception {
        for (int i = 0; i <= 6; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            double d = (double) i / (double) 6;
            ChernoffFace.create(graphics, 0.5, 0.5, d, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5);
            ImageIO.write(image, "PNG", new File(String.format("./pupil-size-%3.2f.png", d)));
        }
    }

    @Test
    public void testNoseSize() throws Exception {
        for (int i = 0; i <= 6; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            double d = (double) i / (double) 6;
            ChernoffFace.create(graphics, 0.5, 0.5, 0.5, 0.5, d, 0.5, 0.5, 0.5, 0.5, 0.5);
            ImageIO.write(image, "PNG", new File(String.format("./nose-size-%3.2f.png", d)));
        }
    }

    @Test
    public void testEyeBrowTilt() throws Exception {
        for (int i = 0; i <= 6; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            double d = (double) i / (double) 6;
            ChernoffFace.create(graphics, 0.5, 0.5, 0.5, d, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5);
            ImageIO.write(image, "PNG", new File(String.format("./eye-brow-tilt-%3.2f.png", d)));
        }
    }

    @Test
    public void testMouthWidth() throws Exception {
        for (int i = 0; i <= 6; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            double d = (double) i / (double) 6;
            ChernoffFace.create(graphics, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, d, 0.5);
            ImageIO.write(image, "PNG", new File(String.format("./mouth-width-%3.2f.png", d)));
        }
    }

    @Test
    public void testMouthHeight() throws Exception {
        for (int i = 0; i <= 6; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            double d = (double) i / (double) 6;
            ChernoffFace.create(graphics, 0.5, 0.5, 0.5, 0.5, 0.5, d, 0.5, 0.5, 0.5, 0.5);
            ImageIO.write(image, "PNG", new File(String.format("./mouth-height-%3.2f.png", d)));
        }
    }

    @Test
    public void testMouthOpeness() throws Exception {
        for (int i = 0; i <= 6; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            double d = (double) i / (double) 6;
            ChernoffFace.create(graphics, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, 0.5, d);
            ImageIO.write(image, "PNG", new File(String.format("./mouth-open-%3.2f.png", d)));
        }
    }

    @Test
    public void testBezier() throws Exception {
        Point2D p1 = new Point2D.Double(0.0, 0.0);
        Point2D p2 = new Point2D.Double(50.0, 0.0);
        for (int i = 0; i < 10; i++) {
            BufferedImage image = new BufferedImage(400, 400, BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics = image.createGraphics();
            Point2D ctl = new Point2D.Double(25.0, (double) i / 10.0 * 25.0);
            ChernoffFace.bezier(graphics, p1, p2, ctl);
            ImageIO.write(image, "PNG", new File(String.format("./bezier-%3.2f-%3.2f.png", ctl.getX(), ctl.getY())));
        }
    }
}
