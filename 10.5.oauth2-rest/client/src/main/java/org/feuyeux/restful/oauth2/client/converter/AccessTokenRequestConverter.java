package org.feuyeux.restful.oauth2.client.converter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;

public class AccessTokenRequestConverter implements GenericConverter {

    private final Set<ConvertiblePair> convertibleTypes = new HashSet<>(
        Collections.singletonList(new ConvertiblePair(AccessTokenRequest.class, AccessTokenRequest.class)));

    public Set<ConvertiblePair> getConvertibleTypes() {
        return convertibleTypes;
    }

    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        return source;
    }

}
