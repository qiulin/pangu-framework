package org.pf9.pangu.framework.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsonorg.JsonOrgModule;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by qiulin on 2017/6/1.
 */
public class JSONUtils {

    public static ObjectMapper objectMapper;

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class)
     * (2)转换为List,如List<Student>,将第二个参数传递为Student
     * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     *
     * @param jsonStr
     * @param valueType
     * @return
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) throws IOException {
        if (objectMapper == null) {
            objectMapper = newObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 把JSON字符串转化成Map<String, String>
     *
     * @param jsonStr
     * @return
     */
    public static HashMap readValueAsMaps(String jsonStr) {
        if (objectMapper == null) {
            objectMapper = newObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, new TypeReference<HashMap>() {
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static JSONObject readValueAsJSONObject(String jsonStr) throws IOException {
        return readValue(jsonStr, JSONObject.class);
    }

    /**
     * json数组转List
     *
     * @param jsonStr
     * @param valueTypeRef
     * @return
     */
    public static <T> T readValue(String jsonStr, TypeReference<T> valueTypeRef) throws IOException {
        if (objectMapper == null) {
            objectMapper = newObjectMapper();
        }

        try {
            return objectMapper.readValue(jsonStr, valueTypeRef);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }


    /**
     * 把JavaBean转换为json字符串
     *
     * @param object
     * @return
     */
    public static String toJSON(Object object) {
        if (objectMapper == null) {
            objectMapper = newObjectMapper();
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static ObjectMapper newObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JsonOrgModule());
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }

}
