package ee.uustal.udisctransformer.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class JsonUtility {
    private static final ObjectMapper simpleMapper;
    private static final ObjectMapper nullMapper;
    private static final TypeReference<TreeMap<String, Object>> TREE_MAP_TYPE_REFERENCE = new TypeReference<>() {
    };

    static {
        SimpleModule multipartModule = new SimpleModule();
        simpleMapper = new ObjectMapper();
        simpleMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        simpleMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        simpleMapper.registerModule(multipartModule);
        simpleMapper.registerModule(new Jdk8Module());
        simpleMapper.registerModule(new JavaTimeModule());

        nullMapper = new ObjectMapper();
        nullMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        nullMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        nullMapper.registerModule(multipartModule);
        nullMapper.registerModule(new ParameterNamesModule());
        nullMapper.registerModule(new Jdk8Module());
        nullMapper.registerModule(new JavaTimeModule());
    }

    public static String prettyPrintJson(String json, boolean writeNulls) throws IOException {
        if (writeNulls) {
            Object object = nullMapper.readValue(json, Object.class);
            return nullMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } else {
            Object object = simpleMapper.readValue(json, Object.class);
            return simpleMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        }
    }

    public static String toJson(Object object) {
        return toJson(object, false);
    }

    public static String toJson(Object object, boolean writeNulls) {
        try {
            if (writeNulls) {
                return nullMapper.writeValueAsString(object);
            } else {
                return simpleMapper.writeValueAsString(object);
            }
        } catch (Exception jpe) {
            throw new RuntimeException(jpe);
        }
    }

    public static byte[] toJsonAsBytes(Object object) {
        return toJsonAsBytes(object, false);
    }

    public static byte[] toJsonAsBytes(Object object, boolean writeNulls) {
        try {
            if (writeNulls) {
                return nullMapper.writeValueAsBytes(object);
            } else {
                return simpleMapper.writeValueAsBytes(object);
            }
        } catch (Exception jpe) {
            throw new RuntimeException(jpe);
        }
    }

    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        try {
            return simpleMapper.readValue(jsonString, clazz);
        } catch (IOException jpe) {
            throw new RuntimeException(jpe);
        }
    }

    public static <T> T fromJson(InputStream is, Class<T> clazz) {
        try {
            return simpleMapper.readValue(is, clazz);
        } catch (IOException jpe) {
            throw new RuntimeException(jpe);
        }
    }

    public static <T> T fromJsonWithTypeReference(String jsonString, TypeReference<T> clazz) {
        try {
            return simpleMapper.readValue(jsonString, clazz);
        } catch (IOException jpe) {
            throw new RuntimeException(jpe);
        }
    }

    public static <T> Set<T> fromJsonToSet(String jsonInput, Class<T> clazz) {
        try {
            return simpleMapper.readValue(jsonInput, simpleMapper.getTypeFactory().constructCollectionType(Set.class, clazz));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> fromJsonToList(String jsonInput, Class<T> clazz) {
        try {
            return simpleMapper.readValue(jsonInput, simpleMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> fromJsonToList(String jsonInput, JavaType javaType) {
        try {
            return simpleMapper.readValue(jsonInput, simpleMapper.getTypeFactory().constructCollectionType(List.class, javaType));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Object> convertToMap(Object content) {
        String jsonString = JsonUtility.toJson(content);
        return JsonUtility.fromJsonWithTypeReference(
                jsonString,
                TREE_MAP_TYPE_REFERENCE
        );
    }

    public static Map<String, Object> convertToFilteredMap(Object content, List<String> filter) {
        if (content == null) {
            return null;
        }
        Map<String, Object> filteredMap = convertToMap(content);
        return filteredMap.entrySet().stream()
                .filter(entry -> filter.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static String emptyJson() {
        return "{}";
    }

    public static ObjectMapper getSimpleMapper() {
        return simpleMapper;
    }

    public static ObjectMapper getNullMapper() {
        return nullMapper;
    }
}