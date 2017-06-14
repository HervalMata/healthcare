package com.healthcare.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Strings;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * JSON-converter
 */
public class UserTypeJsonConverter {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE)
            .setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE)
            .setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);

    static {
        OBJECT_MAPPER.setConfig(
                OBJECT_MAPPER.getSerializationConfig().without(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS)
        );
    }

    private UserTypeJsonConverter() {
    }

    /**
     * Convert bean to json string
     *
     * @param object    object to convert
     *
     * @return json string
     */
    public static String toJsonString(@Nullable Object object){
        String result = "{}";

        try {
            if (null != object) {
                result = OBJECT_MAPPER.writeValueAsString(object);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    /**
     * Create bean from json string
     *
     * @param string json string
     *
     * @return converted object
     */
    public static Object fromJsonString(@Nullable String string, Class targetClass){
        Object result = null;

        try {
            if (!Strings.isNullOrEmpty(string)) {
                result = OBJECT_MAPPER.readValue(string, targetClass);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
