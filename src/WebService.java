import org.siani.itrules.FrameDeserializer;
import org.siani.itrules.TemplateEngine;
import org.siani.itrules.model.Frame;
import spark.Request;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static spark.Spark.get;

/**
 * Created by Osvaldo on 10/12/2015.
 */
public class WebService {
    public static void main(String[] args) {

        get("/it-rules", (request, responsive) ->{
            writeRuleInFile(request);
            Frame deserializer = (Frame) FrameDeserializer.deserializer(getFrame(request));
            TemplateEngine templateEngine = new TemplateEngine().use("template.itr");
            return templateEngine.render(deserializer);
        });
    }

    private static void writeRuleInFile(Request request) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("template.itr")));
        bufferedWriter.write(getRule(request));
        bufferedWriter.close();
    }

    private static String getFrame(Request request) {
        return request.queryMap().get("frame").value();
    }

    private static String getRule(Request request) {
        return request.queryMap().get("rule").value();
    }
}
