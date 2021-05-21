package net.remgant.graphics;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class ChernoffFace {

    final static private AffineTransform IDENTITY_TRANSFORM = new AffineTransform(1.0, 0.0, 0.0, 1.0, 0.0, 0.0);

    public static void create(Graphics2D graphics, double head, double eyeShape, double pupilSize, double eyeBrowTilt, double noseSize,
                              double mouthHeight, double eyeSpacing, double eyeSize, double mouthWidth, double mouthOpeness) throws InvalidValueException {
        create(graphics, IDENTITY_TRANSFORM, head, eyeShape, pupilSize, eyeBrowTilt, noseSize, mouthHeight, eyeSpacing, eyeSize, mouthWidth, mouthOpeness);
    }

    /**
     *
     * @param graphics
     * @param transform
     * @param head
     * @param eyeShape
     * @param pupilSize
     * @param eyeBrowTilt
     * @param noseSize
     * @param mouthHeight
     * @param eyeSpacing
     * @param eyeSize
     * @param mouthWidth
     * @param mouthOpeness
     * @throws InvalidValueException
     */
    public static void create(Graphics2D graphics, AffineTransform transform, double head, double eyeShape, double pupilSize, double eyeBrowTilt, double noseSize,
                              double mouthHeight, double eyeSpacing, double eyeSize, double mouthWidth, double mouthOpeness) throws InvalidValueException {
        java.util.List<Area> drawList = new ArrayList<>();
        java.util.List<Area> fillList = new ArrayList<>();

        if (head < 0.0 || head > 1.0)
            throw new InvalidValueException("head");
        if (eyeShape < 0.0 || eyeShape > 1.0)
            throw new InvalidValueException("eye shape");
        if (pupilSize < 0.0 || pupilSize > 1.0)
            throw new InvalidValueException("pupil size");
        if (noseSize < 0.0 || noseSize > 1.0)
            throw new InvalidValueException("nose size");
        if (mouthHeight < 0.0 || mouthHeight > 1.0)
            throw new InvalidValueException("mouth height");
        if (eyeSpacing < 0.0 || eyeSpacing > 1.0)
            throw new InvalidValueException("eye spacing");
        if (eyeSize < 0.0 || eyeSize > 1.0)
            throw new InvalidValueException("eye size");
        if (mouthWidth < 0.0 || mouthWidth > 1.0)
            throw new InvalidValueException("mouth width");
        if (mouthOpeness < 0.0 || mouthOpeness > 1.0)
            throw new InvalidValueException("mouth openess");

        double width = 60.0;
        double height = 60.0;
        double xOffset = 0.0;
        if (head < 0.5) {
            width = width - (0.5 - head) * 20.0;
            xOffset = (60.0 - width) / 2.0;
        } else if (head > 0.5) {
            height = height - (head - 0.5) * 20.0;
        }
        Ellipse2D h = new Ellipse2D.Double(xOffset, 0.0, width, height);
        drawList.add(new Area(h));


        // Eyes have 3 attributes:  shape (i.e. eccentricity, how eillipitical it is), distance apart and size.
        // default eye size is 12.5

        // eyesize min of 5, max of 15
        width = 5.0 + eyeSize * 15.0;
        height = 5.0 + eyeSize * 15.0;

        if (eyeShape < 0.5) {
            width -= width / 2.0 * eyeShape;
        } else if (eyeShape > 0.5) {
            height -= height / 2.0 * eyeShape;
        }

        double eyeOffset = (0.5 - eyeSpacing) * 10.0;

        // Default center of eyes is at (20,20) and (40,20)
        Ellipse2D le = new Ellipse2D.Double(20.0 - width / 2.0 - eyeOffset, 20.0 - height / 2.0, width, height);
        drawList.add(new Area(le));
        Ellipse2D re = new Ellipse2D.Double(40.0 - width / 2.0 + eyeOffset, 20.0 - height / 2.0, width, height);
        drawList.add(new Area(re));

        // Now do pupil size, reusing eyeOffset
        // Max pupil size should be less than min eye size
        // Default size is 2.5, Shrink or expand by a maximum of 2
        width = 0.5 + pupilSize * 4.0;
        height = 0.5 + pupilSize * 4.0;
        Ellipse2D lp = new Ellipse2D.Double(20.0 - width / 2.0 - eyeOffset, 20.0 - height / 2.0, width, height);
        fillList.add(new Area(lp));
        Ellipse2D rp = new Ellipse2D.Double(40.0 - width / 2.0 + eyeOffset, 20.0 - height / 2.0, width, height);
        fillList.add(new Area(rp));

        // Nose
        // Top of nose is (30,20)
        // Base is at (25,length) and (35, length)

        double length = 5.0 + noseSize * 10.0;

        Path2D nose = new Path2D.Double();
        nose.moveTo(30, 20);
        nose.lineTo(27.5, 20 + length);
        nose.lineTo(32.5, 20 + length);
        nose.lineTo(30, 20);
        drawList.add(new Area(nose));


        // eyebrow tilt
        double eyeBrowOffset = (0.5 - eyeBrowTilt) * 10.0;

        Path2D leb = new Path2D.Double();
        leb.moveTo(15, 10 + eyeBrowOffset);
        leb.lineTo(25, 10 - eyeBrowOffset);
        leb.lineTo(25, 10.5 - eyeBrowOffset);
        leb.lineTo(15, 10.5 + eyeBrowOffset);
        leb.lineTo(15, 10 + eyeBrowOffset);
        fillList.add(new Area(leb));
        Path2D reb = new Path2D.Double();
        reb.moveTo(35, 10 - eyeBrowOffset);
        reb.lineTo(45, 10 + eyeBrowOffset);
        reb.lineTo(45, 10.5 + eyeBrowOffset);
        reb.lineTo(35, 10.5 - eyeBrowOffset);
        reb.lineTo(35, 10 - eyeBrowOffset);
        fillList.add(new Area(reb));


        // Smile has three attributes:
        // height of the mouth (on the y-axis i.e. how smiley or frowny it is)
        // width of the mouth (on the x-axis)
        // how open the mouth is (distance between lips

        Area mouthArea = new Area();

        double mouthWidthOffset = (0.5 - mouthWidth) * 12.5;
        double mouthHeightOffset = (mouthHeight - 0.5) * 2.0;
        double mouthOpenessOffset = mouthOpeness * 10.0 + 2.5;

        Point2D p1 = new Point2D.Double(17.5 + mouthWidthOffset, 40);
        Point2D p2 = new Point2D.Double(42.5 - mouthWidthOffset, 40);

        Point2D cpTop = new Point2D.Double(30.0, 40 + mouthHeightOffset * 20.0);

        Point2D cpBot = new Point2D.Double(30.0, 40 + mouthHeightOffset * 20.0  + mouthOpenessOffset);

        QuadCurve2D top = new QuadCurve2D.Double(p1.getX(), p1.getY(), cpTop.getX(), cpTop.getY(), p2.getX(), p2.getY());
        QuadCurve2D bottom = new QuadCurve2D.Double(p1.getX(), p1.getY(), cpBot.getX(), cpBot.getY(), p2.getX(), p2.getY());

        if (mouthHeight < 0.5) {
            mouthArea.add(new Area(top));
            mouthArea.subtract(new Area(bottom));
        } else if (mouthHeight > 0.5) {
            mouthArea.add(new Area(bottom));
            mouthArea.subtract(new Area(top));
        } else {
            mouthArea.add(new Area(top));
            mouthArea.add(new Area(bottom));
        }
        drawList.add(mouthArea);

        AffineTransform affineTransform = AffineTransform.getTranslateInstance(50.0, 50.0);
        if (!transform.isIdentity())
            affineTransform.concatenate(transform);
        affineTransform.concatenate(AffineTransform.getScaleInstance(4.0, 4.0));

        graphics.setColor(Color.BLACK);

        for (Area a : drawList) {
            a.transform(affineTransform);
            graphics.draw(a);
        }
        for (Area a : fillList) {
            a.transform(affineTransform);
            graphics.fill(a);
        }
    }

    public static void bezier(Graphics2D graphics, Point2D p1, Point2D p2, Point2D ctl) {
        QuadCurve2D quadCurve = new QuadCurve2D.Double(p1.getX(), p2.getY(), ctl.getX(), ctl.getY(), p2.getX(), p2.getY());
        AffineTransform affineTransform = AffineTransform.getTranslateInstance(100.0, 100.0);
        affineTransform.concatenate(AffineTransform.getScaleInstance(4.0, 4.0));
        Area area = new Area(quadCurve);
        area.transform(affineTransform);
        graphics.setColor(Color.BLACK);
//        graphics.draw(area);
        graphics.draw(quadCurve);
    }
}
