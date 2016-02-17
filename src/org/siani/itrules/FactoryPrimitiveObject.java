package org.siani.itrules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class FactoryPrimitiveObject {

    private static Map<String, Caster> casterMap = new HashMap<>();

    static {
        casterMap.put("String", value -> value);
        casterMap.put("Double", value -> Double.valueOf(value));
        casterMap.put("Long", value -> Long.valueOf(value));
        casterMap.put("Date", value -> toDate(value));
        casterMap.put("Integer", value -> Integer.valueOf(value));
        casterMap.put("Byte", value -> Byte.valueOf(value));
        casterMap.put("Boolean", value -> Boolean.valueOf(value));
        casterMap.put("Short", value -> Short.valueOf(value));
    }

    private static Date toDate(String value) {
        try {
            return new SimpleDateFormat("dd/MM/yyyy").parse(value);
        } catch (ParseException e) {
            throw new RuntimeException("Format in " + value + " is not correct");
        }
    }

    public static Object build(String value, String type) throws ParseException {
        return casterMap.get(type).cast(value);
    }

    private interface Caster {

        public Object cast(String value);

    }
}
