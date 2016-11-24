package com.morfolo.loffy.apis.converters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by MORFOLO on 24/11/2016.
 */

public class FastJsonResponseBody<T> implements Converter<ResponseBody, T> {

    private ParserConfig mParserConfig;
    private Feature[] EMPTY_FEATURE = new Feature[0];
    private Feature[] mFeature;
    private int featureValues;
    private Type type;

    public FastJsonResponseBody(ParserConfig mParserConfig, Feature[] mFeature, int featureValues, Type type) {
        this.mParserConfig = mParserConfig;
        this.mFeature = mFeature;
        this.featureValues = featureValues;
        this.type = type;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        try {
            return JSON.parseObject(value.toString(), type, mParserConfig, featureValues, mFeature != null ? mFeature : EMPTY_FEATURE);
        } finally {
            value.close();
        }
    }
}
