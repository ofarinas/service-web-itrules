package operation;

import org.siani.itrules.model.AbstractFrame;
import org.siani.itrules.model.Frame;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * Created by Osvaldo on 1/22/2016.
 */
public class FrameCompare {

    public static  boolean equals(AbstractFrame frameA ,AbstractFrame frameB) {
        if (frameA.isPrimitive() & frameB.isPrimitive()) {
            if(!frameA.value().equals(frameB.value()) )return false;
        }
        else {

            if (!Arrays.equals(((Frame) frameA).types(), ((Frame) frameB).types()) || !Arrays.equals(((Frame) frameA).slots(), ((Frame) frameB).slots()))
                return false;

            else {
                for (String slot : ((Frame) frameA).slots()) {
                    if (!equals(frameA.frames(slot).next(), frameB.frames(slot).next())) return false;
                }

            }
        }
        return true;
    }

    public static void main(String[] args) throws ParseException {
//        Frame jsonFrame =  (Frame) FrameDeserializer.deserializer(new JsonFileReader().readResource("json/OperationalAttributes.json"));
        Frame testFrame = new Frame();
        testFrame.addTypes("roster");
        testFrame.addFrame("Coach",new Frame().addTypes("person")
                .addFrame("Name","Juan Antonio")
                .addFrame("Birthday",new SimpleDateFormat("dd/MM/yyyy").parse("17/06/1980"))
                .addFrame("Country","Spain").addFrame("Club","L.A. Lakers"))
                .addFrame("Player", new Frame[]{new Frame().addTypes("person")
                        .addFrame("Name", "Pau Gasol")
                        .addFrame("Birthday", new SimpleDateFormat("dd/MM/yyyy").parse("06/07/1980"))
                        .addFrame("Country", "Spain")
                        .addFrame("Club", "L.A. Lakers"), new Frame().addTypes("person")
                        .addFrame("Name", "Rudy Fernandez")
                        .addFrame("Birthday", new SimpleDateFormat("dd/MM/yyyy").parse("04/04/1985"))
                        .addFrame("Country", "Spain")
                        .addFrame("Club", "L.A. Lakers"), new Frame().addTypes("person")
                        .addFrame("Name", "Juan Carlos Navarro")
                        .addFrame("Birthday", new SimpleDateFormat("dd/MM/yyyy").parse("17/06/1980"))
                        .addFrame("Country", "Spain")
                        .addFrame("Club", "L.A. Lakers")});
        Frame testFrame2 = new Frame();
        testFrame2.addTypes("roster");
        testFrame2.addFrame("Coach",new Frame().addTypes("person")
                .addFrame("Name","Juan Antonio")
                .addFrame("Birthday",new SimpleDateFormat("dd/MM/yyyy").parse("17/06/1980"))
                .addFrame("Country","Spain").addFrame("Club","L.A. Lakers"))
                .addFrame("Player", new Frame[]{new Frame().addTypes("person")
                        .addFrame("Name", "Pau Gasol")
                        .addFrame("Birthday", new SimpleDateFormat("dd/MM/yyyy").parse("06/07/1980"))
                        .addFrame("Country", "Spain")
                        .addFrame("Club", "L.A. Lakers"), new Frame().addTypes("person")
                        .addFrame("Name", "Rudy Fernandez")
                        .addFrame("Birthday", new SimpleDateFormat("dd/MM/yyyy").parse("04/04/1985"))
                        .addFrame("Country", "Spain")
                        .addFrame("Club", "L.A. Lakers"), new Frame().addTypes("person")
                        .addFrame("Name", "Juan Carlos Navarro")
                        .addFrame("Birthday", new SimpleDateFormat("dd/MM/yyyy").parse("17/06/1980"))
                        .addFrame("Country", "Spain")
                        .addFrame("Club", "L.A. Lakers")});

        System.out.println(equals(testFrame2, testFrame));

    }
}
