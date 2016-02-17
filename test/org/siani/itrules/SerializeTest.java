package org.siani.itrules;

public class SerializeTest {

//    @Test
//    public void pauGasol()  {
//        Frame frame = new Frame("Person");
//        frame.addFrame("Name", "Paul Gasol");
//        try {
//            frame.addFrame("Birthday", getDate("07/06/1980"));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        frame.addFrame("Country", "Spain");
//        Assert.assertEquals(readResource("json/PauGasol.json"), serialize(frame));
//    }
//
//    @Test
//    public void optionalAttributes() throws Exception {
//        Frame frame = new Frame("Roster");
//        frame.addFrame("Coach", new Frame("Person") {{
//            addFrame("Name", "Juan Antonio Orenga");
//            addFrame("Birthday", getDate("29/07/1966"));
//            addFrame("Country", "Spain");
//        }});
//        frame.addFrame("Player", new Frame("Person") {{
//            addFrame("Name", "Pau Gasol");
//            addFrame("Birthday", getDate("06/07/1980"));
//            addFrame("Country", "Spain");
//            addFrame("Club", "L.A. Lakers");
//        }});
//        frame.addFrame("Player", new Frame("Person") {{
//            addFrame("Name", "Rudy Fernandez");
//            addFrame("Birthday", getDate("04/04/1985"));
//            addFrame("Country", "Spain");
//        }});
//        frame.addFrame("Player", new Frame("Person") {{
//            addFrame("Name", "Juan Carlos Navarro");
//            addFrame("Birthday", getDate("17/06/1980"));
//            addFrame("Country", "Spain");
//        }});
//        Assert.assertEquals(readResource("json/OperationalAttributes.json"),serialize(frame));
//    }
//
//
//    private String serialize(Frame frame) {
//        return removeSpaces(FrameSerializer.serialize(frame));
//    }
//
//    private String readResource(String resource) {
//        return removeSpaces(new JsonFileReader().readResource(resource));
//    }
//
//    private String removeSpaces(String string) {
//        return string.replaceAll("(\\s|\\n)","");
//    }
//
//    private Date getDate(String date) throws ParseException {
//
//        return  new SimpleDateFormat("dd/MM/yyyy").parse(date);
//    }

}
