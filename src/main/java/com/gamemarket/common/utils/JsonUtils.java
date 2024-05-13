package com.gamemarket.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamemarket.common.exception.parse.ParseException;
import com.gamemarket.common.exception.parse.ParseExceptionCode;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> String objectToJson(T object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ParseException(ParseExceptionCode.JSON_PARSER_EXCEPTION);
        }
    }

}
