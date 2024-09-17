package org.vaadin.marcus.config;

import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.util.Optional;

public class OptionalTypeAdapter {
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType != Optional.class) {
                return null;
            }
            final TypeAdapter<?> innerAdapter = gson.getAdapter(TypeToken.get(Optional.class.getTypeParameters()[0]));
            return new TypeAdapter<T>() {
                @Override
                public void write(JsonWriter out, T value) throws IOException {
                    if (value == null) {
                        out.nullValue();
                    } else {
                        Optional<?> optional = (Optional<?>) value;
                        if (optional.isPresent()) {
                            innerAdapter.write(out, optional.get());
                        } else {
                            out.nullValue();
                        }
                    }
                }

                @Override
                public T read(JsonReader in) throws IOException {
                    return (T) Optional.ofNullable(innerAdapter.read(in));
                }
            };
        }
    };
}