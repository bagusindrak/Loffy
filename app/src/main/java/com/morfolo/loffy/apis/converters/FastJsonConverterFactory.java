package com.morfolo.loffy.apis.converters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by MORFOLO on 24/11/2016.
 */

public class FastJsonConverterFactory extends Converter.Factory {

    private ParserConfig mParserConfig = ParserConfig.getGlobalInstance();
    private int feateureValues = JSON.DEFAULT_PARSER_FEATURE;
    private Feature[] mFeature;
    private SerializeConfig mSerializeConfig;
    private SerializerFeature[] mSerializerFeature;

    public FastJsonConverterFactory() {
    }

    public static FastJsonConverterFactory create() {
        return new FastJsonConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        super.responseBodyConverter(type, annotations, retrofit);
        return new FastJsonResponseBody<>(mParserConfig, mFeature, feateureValues, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
        return new FastJsonRequestBody<>(mSerializeConfig, mSerializerFeature);
    }

    //SETTER dan GETTER

    public ParserConfig getmParserConfig() {
        return mParserConfig;
    }

    public FastJsonConverterFactory setmParserConfig(ParserConfig mParserConfig) {
        this.mParserConfig = mParserConfig;
        return this;
    }

    public int getFeateureValues() {
        return feateureValues;
    }

    public FastJsonConverterFactory setFeateureValues(int feateureValues) {
        this.feateureValues = feateureValues;
        return this;
    }

    public Feature[] getmFeature() {
        return mFeature;
    }

    public FastJsonConverterFactory setmFeature(Feature[] mFeature) {
        this.mFeature = mFeature;
        return this;
    }

    public SerializeConfig getmSerializeConfig() {
        return mSerializeConfig;
    }

    public FastJsonConverterFactory setmSerializeConfig(SerializeConfig mSerializeConfig) {
        this.mSerializeConfig = mSerializeConfig;
        return this;
    }

    public SerializerFeature[] getmSerializerFeature() {
        return mSerializerFeature;
    }

    public FastJsonConverterFactory setmSerializerFeature(SerializerFeature[] mSerializerFeature) {
        this.mSerializerFeature = mSerializerFeature;
        return this;
    }
}
