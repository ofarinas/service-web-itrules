package org.siani.itrules;

import com.google.gson.*;
import org.siani.itrules.model.AbstractFrame;
import org.siani.itrules.model.Frame;
import org.siani.itrules.model.PrimitiveFrame;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Iterator;

public class FrameSerializer {
    public static String serialize(AbstractFrame frame) {
        GsonBuilder gson = new GsonBuilder();
        gson.setPrettyPrinting();
        gson.registerTypeAdapter(PrimitiveFrame.class, new PrimitiveFrameAdapter());
        gson.registerTypeAdapter(Frame.class, new FrameAdapter());

        return gson.create().toJson(frame);
    }

    private static class PrimitiveFrameAdapter implements JsonSerializer<PrimitiveFrame> {

        @Override
        public JsonElement serialize(PrimitiveFrame primitiveFrame, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("@type", getType(primitiveFrame));
            jsonObject.addProperty("@value", getValue(primitiveFrame));
            return jsonObject;
        }

        private static String getType(PrimitiveFrame primitiveFrame) {
            String typeClass = primitiveFrame.value().getClass().toString();
            return typeClass.substring(typeClass.lastIndexOf(".") + 1);
        }

        private static String getValue(PrimitiveFrame primitiveFrame) {

            return getType(primitiveFrame).equals("Date") ? new SimpleDateFormat("dd/MM/yyyy").format(primitiveFrame.value()):primitiveFrame.value().toString();
        }
    }


    private static class FrameAdapter implements JsonSerializer<Frame> {
        @Override
        public JsonElement serialize(Frame frame, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("@types", jsonSerializationContext.serialize(frame.types()));
            for (String slot : frame.slots())
                jsonObject.add(slot, serialize(frame.frames(slot), jsonSerializationContext));
            return jsonObject;

        }

        private JsonElement serialize(Iterator<AbstractFrame> frames, JsonSerializationContext jsonSerializationContext) {
            JsonElement serialize = null;
            AbstractFrame abstractFrame = null;
            while (frames.hasNext()) {
                abstractFrame = frames.next();
                serialize = abstractFrame instanceof Frame ? add(serialize, jsonSerializationContext.serialize(abstractFrame, Frame.class))
                        : add(serialize, jsonSerializationContext.serialize(abstractFrame, PrimitiveFrame.class));
            }
            return serialize;
        }

        private JsonElement add(JsonElement serialize, JsonElement item) {
            if (serialize == null) return item;
            JsonArray array;
            if (serialize instanceof JsonObject) {
                array = new JsonArray();
                array.add(serialize);
            } else {
                array = (JsonArray) serialize;
            }
            array.add(item);
            return array;
        }
    }
}
