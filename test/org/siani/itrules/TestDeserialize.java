package org.siani.itrules;

import operation.JsonFileReader;
import org.junit.Test;
import org.siani.itrules.model.AbstractFrame;
import org.siani.itrules.model.Frame;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TestDeserialize {

    @Test
    public void pauGasol() throws FileNotFoundException, ParseException {
        Frame frame2= (Frame) FrameDeserializer.deserializer(new JsonFileReader().readResource("json/PauGasol.json"));
        assertEquals(frame2.types()[0], "person");
        assertArrayEquals(frame2.slots(), new String[]{"name", "birthday", "country"});
        assertEquals(frame2.frames("Name").next().value(),"Paul Gasol");
        assertEquals(frame2.frames("Birthday").next().value(), new SimpleDateFormat("dd/MM/yyyy").parse("07/06/1980"));
        assertEquals(frame2.frames("Country").next().value(), "Spain");
        TemplateEngine templateEngine =new TemplateEngine().use("testDate-res/template/pauGasol.itr");
        System.out.println(templateEngine.render(frame2));
    }


    @Test
    public void optionalAttributesTest() throws Exception {
        Frame frame2 = (Frame) FrameDeserializer.deserializer(new JsonFileReader().readResource("json/OperationalAttributes.json"));
        assertEquals("roster", frame2.types()[0]);
        assertArrayEquals(frame2.slots(), new String[]{"coach", "player"});
        assertEquals(((Frame) frame2.frames("Coach").next()).types()[0], "person");
        assertArrayEquals(((Frame) frame2.frames("Coach").next()).slots(), new String[]{"name", "birthday", "country"});
        assertEquals(((Frame) frame2.frames("Coach").next()).frames("Name").next().value(), "Juan Antonio Orenga");
        assertEquals(((Frame) frame2.frames("Coach").next()).frames("Birthday").next().value(), new SimpleDateFormat("dd/MM/yyyy").parse("29/07/1966"));
          assertEquals(((Frame) frame2.frames("Coach").next()).frames("Country").next().value(), "Spain");
        Iterator<AbstractFrame> abstractFrameIterator = frame2.frames("Player");
        Frame framePayer1 = (Frame) abstractFrameIterator.next();
        assertEquals(framePayer1.frames("Country").next().value(), "Spain");
        assertEquals(framePayer1.slots()[0], "name");
        assertEquals(framePayer1.slots()[1], "birthday");
        assertEquals(framePayer1.slots()[2], "country");
        assertEquals(framePayer1.slots()[3], "club");
        assertEquals(framePayer1.types()[0], "person");
        assertEquals(framePayer1.frames("Name").next().value(), "Pau Gasol");
        assertEquals(framePayer1.frames("Birthday").next().value(), new SimpleDateFormat("dd/MM/yyyy").parse("06/07/1980"));
        assertEquals(framePayer1.frames("Country").next().value(), "Spain");
        assertEquals(framePayer1.frames("Club").next().value(), "L.A. Lakers");
        framePayer1 = (Frame) abstractFrameIterator.next();
        assertEquals(framePayer1.frames("Country").next().value(), "Spain");
        assertEquals(framePayer1.slots()[0], "name");
        assertEquals(framePayer1.slots()[1], "birthday");
        assertEquals(framePayer1.slots()[2], "country");
        assertEquals(framePayer1.types()[0], "person");
        assertEquals(framePayer1.frames("Name").next().value(), "Rudy Fernandez");
        assertEquals(framePayer1.frames("Birthday").next().value(), new SimpleDateFormat("dd/MM/yyyy").parse("04/04/1985"));
        assertEquals(framePayer1.frames("Country").next().value(), "Spain");
        framePayer1 = (Frame) abstractFrameIterator.next();
        assertEquals(framePayer1.frames("Country").next().value(), "Spain");
        assertEquals(framePayer1.slots()[0], "name");
        assertEquals(framePayer1.slots()[1], "birthday");
        assertEquals(framePayer1.slots()[2], "country");
        assertEquals(framePayer1.types()[0], "person");
        assertEquals(framePayer1.frames("Name").next().value(), "Juan Carlos Navarro");
        assertEquals(framePayer1.frames("Birthday").next().value(), new SimpleDateFormat("dd/MM/yyyy").parse("17/06/1980"));
        assertEquals(framePayer1.frames("Country").next().value(), "Spain");
    }

    @Test
    public void testMessage() throws Exception {
        Frame message= (Frame) FrameDeserializer.deserializer(new JsonFileReader().readResource("json/message.json"));
        assertEquals("message",message.types()[0]);
        assertEquals("ofarinas12@gmail.com",message.frames("from").next().value());
        Iterator<AbstractFrame> to = message.frames("to");
        assertEquals("ofarrom@gmail.com",to.next().value());
        assertEquals("ofarinas@outlook.com",to.next().value());
        assertEquals("father",message.frames("subject").next().value());
        assertEquals("Que tal todo en el Hospital",message.frames("body").next().value());

    }
}


