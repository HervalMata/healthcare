package com.healthcare.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.base.Strings;
import com.healthcare.model.ReviewForm;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * JSON-converter for {@link com.healthcare.model.ReviewForm}
 */
public class ReviewFormJsonConverter {

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

    private ReviewFormJsonConverter() {
    }

    /**
     * Convert bean to json string
     *
     * @param reviewForm    review form
     *
     * @return json string
     */
    public static String toJsonString(@Nullable ReviewForm reviewForm){
        String result = "{}";

        try {
            if (null != reviewForm) {
                result = OBJECT_MAPPER.writeValueAsString(reviewForm);
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
     * @return review form
     */
    public static ReviewForm fromJsonString(@Nullable String string){
        ReviewForm result = null;

        try {
            if (!Strings.isNullOrEmpty(string)) {
                result = OBJECT_MAPPER.readValue(string, ReviewForm.class);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
