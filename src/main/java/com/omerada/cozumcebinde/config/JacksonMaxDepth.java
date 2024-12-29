package com.omerada.cozumcebinde.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.*;
import org.springframework.stereotype.Component;


@Component
public class JacksonMaxDepth {
//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
//        objectMapper.setFilterProvider(
//                new SimpleFilterProvider().
//                        addFilter(DepthAwarePropertyFilter.FILTER_ID,
//                                new DepthAwarePropertyFilter(MAX_OBJECT_DEPTH_ON_SERIALIZATION)));
//        objectMapper.registerModule(new SimpleModule() {
//            @Override
//            public void setupModule(SetupContext context) {
//                super.setupModule(context);
//                context.addBeanSerializerModifier(new DepthAwareBeanSerializerModifier());
//            }
//        });
//        MappingJackson2HttpMessageConverter converter =
//                new MappingJackson2HttpMessageConverter(objectMapper);
//        return converter;
//    }

    public static final int MAX_OBJECT_DEPTH_ON_SERIALIZATION = 5;

    public static class DepthAwarePropertyFilter extends SimpleBeanPropertyFilter {

        public static final String FILTER_ID = "DepthAwarePropertyFilter";

        public final int maxDepth;

        public DepthAwarePropertyFilter(int maxDepth) {
            this.maxDepth = maxDepth;
        }

        public int calculateDepth(PropertyWriter writer, JsonGenerator jgen) {
            JsonStreamContext sc = jgen.getOutputContext();
            int depth = -1;
            while (sc != null) {
                sc = sc.getParent();
                depth++;
            }
            return depth;
        }

        @Override
        public void serializeAsField(Object pojo, JsonGenerator gen, SerializerProvider provider, PropertyWriter writer)
                throws Exception {
            int depth = calculateDepth(writer, gen);
            if (depth <= maxDepth) {
                try {
                    writer.serializeAsField(pojo, gen, provider);
                } catch (Throwable t) {
                    writer.serializeAsOmittedField(pojo, gen, provider);
                }
            } else {
                // comment this if you don't want {} placeholders
                writer.serializeAsOmittedField(pojo, gen, provider);
            }
        }

    }

    public static class DepthAwareBeanSerializerModifier extends BeanSerializerModifier {

        @Override
        public JsonSerializer<?> modifySerializer(
                SerializationConfig config, BeanDescription desc, JsonSerializer<?> serializer) {
            return serializer.withFilterId(DepthAwarePropertyFilter.FILTER_ID);
        }

        @Override
        public JsonSerializer<?> modifyArraySerializer(
                SerializationConfig config, ArrayType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
            return serializer.withFilterId(DepthAwarePropertyFilter.FILTER_ID);
        }

        @Override
        public JsonSerializer<?> modifyCollectionSerializer(
                SerializationConfig config, CollectionType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
            return serializer.withFilterId(DepthAwarePropertyFilter.FILTER_ID);
        }

        @Override
        public JsonSerializer<?> modifyCollectionLikeSerializer(
                SerializationConfig config, CollectionLikeType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
            return serializer.withFilterId(DepthAwarePropertyFilter.FILTER_ID);
        }

        @Override
        public JsonSerializer<?> modifyMapSerializer(
                SerializationConfig config, MapType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
            return serializer.withFilterId(DepthAwarePropertyFilter.FILTER_ID);
        }

        @Override
        public JsonSerializer<?> modifyMapLikeSerializer(
                SerializationConfig config, MapLikeType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
            return serializer.withFilterId(DepthAwarePropertyFilter.FILTER_ID);
        }

        @Override
        public JsonSerializer<?> modifyEnumSerializer(
                SerializationConfig config, JavaType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
            return serializer.withFilterId(DepthAwarePropertyFilter.FILTER_ID);
        }

        @Override
        public JsonSerializer<?> modifyKeySerializer(
                SerializationConfig config, JavaType valueType, BeanDescription beanDesc, JsonSerializer<?> serializer) {
            return serializer.withFilterId(DepthAwarePropertyFilter.FILTER_ID);
        }

    }

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.setFilterProvider(
                new SimpleFilterProvider().
                        addFilter(DepthAwarePropertyFilter.FILTER_ID,
                                new DepthAwarePropertyFilter(MAX_OBJECT_DEPTH_ON_SERIALIZATION)));
        objectMapper.registerModule(new SimpleModule() {
            @Override
            public void setupModule(SetupContext context) {
                super.setupModule(context);
                context.addBeanSerializerModifier(new DepthAwareBeanSerializerModifier());
            }
        });
    }

}
