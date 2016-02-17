package org.siani.itrules;

import com.google.gson.*;
import org.siani.itrules.model.AbstractFrame;
import org.siani.itrules.model.Frame;
import org.siani.itrules.model.PrimitiveFrame;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by osvaldo on 11/28/14.
 */
public class FrameDeserializer {


    public static AbstractFrame deserializer(String json) {
        GsonBuilder gson = new GsonBuilder();
        gson.registerTypeAdapter(AbstractFrame.class, new AbstractFrameAdapter());
        gson.registerTypeAdapter(PrimitiveFrame.class, new PrimitiveFrameAdapter());
        return gson.create().fromJson(json, AbstractFrame.class);
    }


    private static class AbstractFrameAdapter implements JsonDeserializer<AbstractFrame> {
        @Override
        public AbstractFrame deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            Frame frame = new Frame();
            Iterator<Map.Entry<String, JsonElement>> iterator = getIterator(jsonElement.getAsJsonObject());
            while (iterator.hasNext()) {
                Map.Entry<String, JsonElement> jsonElementMap = iterator.next();
                if (jsonElementMap.getKey().equals("@types"))
                    frame.addTypes((String[]) jsonDeserializationContext.deserialize(jsonElementMap.getValue(), String[].class));
                else
                    DeserializerAbstractFrame(jsonDeserializationContext, frame, jsonElementMap);
            }
            return frame;
        }

        private void DeserializerAbstractFrame(JsonDeserializationContext jsonDeserializationContext, Frame frame, Map.Entry<String, JsonElement> jsonElementMap) {

            if (isFrame(jsonElementMap))
                deserializerFrame(frame, jsonDeserializationContext, jsonElementMap);
            else {
                deserializerPrimitiveFrame(jsonDeserializationContext, frame, jsonElementMap);
            }
        }

        private void deserializerPrimitiveFrame(JsonDeserializationContext jsonDeserializationContext, Frame frame, Map.Entry<String, JsonElement> jsonElementMap) {
            PrimitiveFrame primitiveFrame = jsonDeserializationContext.deserialize(jsonElementMap.getValue(), PrimitiveFrame.class);
            frame.addFrame(jsonElementMap.getKey().toString(), primitiveFrame);
        }

        private boolean isFrame(Map.Entry<String, JsonElement> jsonElementMap) {
            return jsonElementMap.getValue() instanceof JsonArray || jsonElementMap.getValue().getAsJsonObject().get("@types") != null ;
        }

        private void deserializerFrame(Frame frame, JsonDeserializationContext jsonDeserializationContext, Map.Entry<String, JsonElement> jsonElementMap) {
            JsonElement jsonElement = jsonElementMap.getValue();
            if (jsonElement instanceof JsonObject) {
                AbstractFrame abstractFrame = jsonDeserializationContext.deserialize(jsonElement, AbstractFrame.class);
                frame.addFrame(jsonElementMap.getKey().toString(), abstractFrame);
            }
            else {
                deserializeJsonArray(frame, jsonDeserializationContext, jsonElementMap, jsonElement);

            }

        }

        private void deserializeJsonArray(Frame frame, JsonDeserializationContext jsonDeserializationContext, Map.Entry<String, JsonElement> jsonElementMap, JsonElement jsonElement) {
            if (jsonElement instanceof JsonArray) {
                JsonArray jsonArray = jsonElement.getAsJsonArray();
                for (int i = 0; i < jsonArray.size(); i++) {
                    AbstractFrame abstractFrame= !isPrimitiveFrame(jsonArray.get(i))?jsonDeserializationContext.deserialize(jsonArray.get(i), AbstractFrame.class):jsonDeserializationContext.deserialize(jsonArray.get(i), PrimitiveFrame.class);
                    frame.addFrame(jsonElementMap.getKey().toString(),abstractFrame);
                }
            }
        }

        private boolean isPrimitiveFrame(JsonElement jsonElement) {
            return jsonElement.isJsonObject()&&jsonElement.getAsJsonObject().get("@type")!=null ;
        }
    }

    private static Iterator<Map.Entry<String, JsonElement>> getIterator(JsonObject jsonObject) {
        return jsonObject.entrySet().iterator();
    }

    private static class PrimitiveFrameAdapter implements JsonDeserializer<PrimitiveFrame> {

        @Override
        public PrimitiveFrame deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            try {
                return new PrimitiveFrame(FactoryPrimitiveObject.build(getValue(jsonObject), getType(jsonObject)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;

        }

        private String getValue(JsonObject jsonObject) {
            return jsonObject.get("@value").getAsString();
        }

        private String getType(JsonObject jsonObject) {
            return jsonObject.get("@type").getAsString();
        }

    }
}
