package com.example.annotation.method;

import javax.ws.rs.HttpMethod;
import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@HttpMethod(value = "MOVE")
@Documented
public @interface MOVE {
}
