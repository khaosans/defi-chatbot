package org.vaadin.marcus.config;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class OptionalTypeAdapter implements TypeAdapterFactory {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (!Optional.class.isAssignableFrom(type.getRawType())) {
            return null;
        }

        Type innerType = ((ParameterizedType) type.getType()).getActualTypeArguments()[0];
        TypeAdapter<?> innerAdapter = gson.getAdapter(TypeToken.get(innerType));

        return (TypeAdapter<T>) new OptionalAdapter(innerAdapter);
    }

    private static class OptionalAdapter<E> extends TypeAdapter<Optional<E>> {
        private final TypeAdapter<E> innerAdapter;

        OptionalAdapter(TypeAdapter<E> innerAdapter) {
            this.innerAdapter = innerAdapter;
        }

        @Override
        public void write(JsonWriter out, Optional<E> value) throws java.io.IOException {
            if (value.isPresent()) {
                innerAdapter.write(out, value.get());
            } else {
                out.nullValue();
            }
        }

        @Override
        public Optional<E> read(JsonReader in) throws java.io.IOException {
            return Optional.ofNullable(innerAdapter.read(in));
        }
    }

    public static TypeAdapterFactory create() {
        return new OptionalTypeAdapter();
    }
}