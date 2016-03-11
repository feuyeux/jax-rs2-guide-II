package org.feuyeux.restful.oauth2.client.converter;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class AccessTokenRequestConverter implements GenericConverter {

    private final Set<ConvertiblePair> convertibleTypes = new HashSet<>(
            Arrays.asList(new ConvertiblePair(AccessTokenRequest.class, AccessTokenRequest.class)));

    public Set<ConvertiblePair> getConvertibleTypes() {
        return convertibleTypes;
    }

    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return source;
    }

}
