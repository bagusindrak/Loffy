package com.morfolo.loffy.apis.converters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Created by MORFOLO on 24/11/2016.
 */

public class FastJsonRequestBody<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json;charset=UTF-8");

    private SerializeConfig mSerializeConfig;
    private SerializerFeature[] mSerializerFeature;

    public FastJsonRequestBody(SerializeConfig mSerializeConfig, SerializerFeature[] mSerializerFeature) {
        this.mSerializeConfig = mSerializeConfig;
        this.mSerializerFeature = mSerializerFeature;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        byte[] content;

        if (mSerializeConfig != null) {
            if (mSerializerFeature != null) {
                content = JSON.toJSONBytes(value, mSerializeConfig, mSerializerFeature);
            } else {
                content = JSON.toJSONBytes(value, mSerializeConfig);
            }
        } else {
            if (mSerializerFeature != null) {
                content = JSON.toJSONBytes(value, mSerializerFeature);
            } else {
                content = JSON.toJSONBytes(value);
            }
        }
        return RequestBody.create(MEDIA_TYPE, content);
    }
}
