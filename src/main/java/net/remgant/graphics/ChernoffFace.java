package net.remgant.graphics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

public class ChernoffFace {

    public static void create(Graphics2D graphics, double head, double eyeShape, double p3, double p4, double p5,
                              double p6, double eyeSpacing, double eyeSize, double p9, double p10) throws Exception {
        if (head < 0.0 || head > 1.0)
            throw new Exception("bad value for head");
        if (eyeShape < 0.0 || eyeShape > 1.0)
            throw new Exception("bad value for eye shape");
        if (eyeSpacing < 0.0 || eyeSpacing > 1.0)
            throw new Exception("bad value for eye spacing");
        if (eyeSize < 0.0 || eyeSize > 1.0)
            throw new Exception("bad value for eye size");
        Area area = new Area();

        double width = 60.0;
        double height = 60.0;
        if (head < 0.5) {
            width = width - (0.5 - head) * 20.0;
        } else if (head > 0.5) {
            height = height - (head - 0.5) * 20.0;
        }
        Ellipse2D h = new Ellipse2D.Double(0.0, 0.0, width, height);
        area.add(new Area(h));


        // Eyes have 3 attributes:  shape (i.e. eccentricity, how eillipitical it is), distance apart and size.
        // default eye size is 10
        width = 10.0;
        height = 10.0;

        // Shrink or expand by a maximum of 5
        if (eyeSize < 0.5) {
            width = width - (5 * (0.5 - eyeSize) * 2.0);
            height = height - (5 * (0.5 - eyeSize) * 2.0);
        } else if (eyeSize > 0.5) {
            width = width + (5 * eyeSize * 2.0);
            height = height + (5 * eyeSize * 2.0);
        }

        if (eyeShape < 0.5) {
            width = width - (0.5 - eyeShape) * 20.0;
        } else if (eyeShape > 0.5) {
            height = height - (eyeShape - 0.5) * 20.0;
        }

        double eyeOffset = (0.5 - eyeSpacing) * 10.0;

        // Default center of eyes is at (20,20) and (40,20)
        Ellipse2D le = new Ellipse2D.Double(20.0 - width / 2.0 - eyeOffset, 20.0 - height / 2.0, width, height);
        area.subtract(new Area(le));
        Ellipse2D re = new Ellipse2D.Double(40.0 - width / 2.0 + eyeOffset, 20.0 - height / 2.0, width, height);
        area.subtract(new Area(re));

        AffineTransform affineTransform = AffineTransform.getTranslateInstance(50.0, 50.0);
        affineTransform.concatenate(AffineTransform.getScaleInstance(4.0, 4.0));
        area.transform(affineTransform);
        graphics.setColor(Color.BLACK);
        graphics.draw(area);
    }
}
