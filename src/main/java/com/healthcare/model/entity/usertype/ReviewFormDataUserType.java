package com.healthcare.model.entity.usertype;

import com.healthcare.model.ReviewForm;
import com.healthcare.util.ReviewFormJsonConverter;

/**
 * Implementation of Hibernate User Type for {@link com.healthcare.model.ReviewForm}
 */
public class ReviewFormDataUserType extends StringSerializableUserType<ReviewForm> {

    @Override
    public Class returnedClass() {
        return ReviewForm.class;
    }

    @Override
    protected ReviewForm cast(Object obj) {
        return (ReviewForm) obj;
    }

    @Override
    protected ReviewForm deserializeConcrete(String string) {
        return ReviewFormJsonConverter.fromJsonString(string);
    }

    @Override
    protected String serializeConcrete(ReviewForm jobSearchQuery) {
        return ReviewFormJsonConverter.toJsonString(jobSearchQuery);
    }
}
