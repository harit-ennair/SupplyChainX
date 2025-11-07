package org.example.supplychainx.security;


import org.example.supplychainx.model.common.RoleEnum;
import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckRole {
    RoleEnum[] value();
}
