package com.example.resource.bing;

import javax.ws.rs.GET;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

import com.example.resource.BookResource;

/**
 * 
 * @author and0429
 *
 */
@Provider
public class AirDynamicFeature implements DynamicFeature {

    /*
     * (non-Javadoc)
     * @see javax.ws.rs.container.DynamicFeature#configure(javax.ws.rs.container.ResourceInfo, javax.ws.rs.core.FeatureContext)
     */
    @Override
    public void configure(final ResourceInfo resourceInfo, final FeatureContext context) {
        boolean classMatched = BookResource.class.isAssignableFrom(resourceInfo.getResourceClass());
        boolean methodNameMatched = resourceInfo.getResourceMethod().getName().contains("getBookBy");
        boolean methodTypeMatched = resourceInfo.getResourceMethod().isAnnotationPresent(GET.class);
        if (classMatched && (methodNameMatched || methodTypeMatched)) {
            context.register(AirDynamicBindingFilter.class);
        }
    }
}
