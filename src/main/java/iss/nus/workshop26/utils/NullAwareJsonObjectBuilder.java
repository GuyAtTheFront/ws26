package iss.nus.workshop26.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonValue;

public class NullAwareJsonObjectBuilder implements JsonObjectBuilder {
        // Use the Factory Pattern to create an instance.
        public static JsonObjectBuilder wrap(JsonObjectBuilder builder) {
            if (builder == null) {
                throw new IllegalArgumentException("Can't wrap nothing.");
            }
            return new NullAwareJsonObjectBuilder(builder);
            }
    
        // Decorated object per Decorator Pattern.
        private final JsonObjectBuilder builder;
    
        private NullAwareJsonObjectBuilder(JsonObjectBuilder builder) {
          this.builder = builder;
        }
    
        @Override
        public JsonObjectBuilder add(String name, JsonValue value) {
          builder.add(name, (value == null) ? JsonValue.NULL : value);
          return this;
        }
    
    
        @Override
        public JsonObjectBuilder add(String name, String value) {
            if(null == value) {
                builder.addNull(name);
            } else {
                builder.add(name, value);
            }
            return this;
        }
    
        @Override
        public JsonObjectBuilder add(String name, BigInteger value) {
            if(null == value) {
                builder.addNull(name);
            } else {
                builder.add(name, value);
            }
            return this;
        }
    
        @Override
        public JsonObjectBuilder add(String name, BigDecimal value) {
            if(null == value) {
                builder.addNull(name);
            } else {
                builder.add(name, value);
            }
            return this;
        }
    
        @Override
        public JsonObjectBuilder add(String name, int value) {
            builder.add(name, value);
            return this;
        }
    
        // @Override
        // public JsonObjectBuilder addForInteger(String name, Integer value, boolean flag) {
    
        //     if(null == value) {
        //         builder.addNull(name);
        //     } else {
        //         builder.add(name, value.intValue());
        //     }
        //     return this;
        // }
    
        @Override
        public JsonObjectBuilder add(String name, long value) {
            builder.add(name, value);
            return this;
        }
    
        @Override
        public JsonObjectBuilder add(String name, double value) {
            builder.add(name, value);
            return this;
        }
    
        @Override
        public JsonObjectBuilder add(String name, boolean value) {
            builder.add(name, value);
            return this;
        }
    
        @Override
        public JsonObjectBuilder addNull(String name) {
            builder.add(name, JsonValue.NULL);
            return this;
        }
    
        @Override
        public JsonObjectBuilder add(String name, JsonObjectBuilder builder) {
            builder.add(name, builder);
            return this;
        }
    
        @Override
        public JsonObjectBuilder add(String name, JsonArrayBuilder builder) {
            builder.add(Integer.parseInt(name), builder);
            return this;
        }
    
        @Override
        public JsonObject build() {
            return builder.build();
        }
        
}
