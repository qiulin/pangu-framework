package org.pf9.pangu.framework.auth;


import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


/**
 * Created by qiulin on 2017/5/4.
 */
public class AuthoritiesFactory {

    public static List<HashMap> get() {

        Reflections reflections = new Reflections("org.pf9.pangu");

        Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(AuthorityProvider.class);

        List<HashMap> defs = new LinkedList<>();

        for (Class clazz : classesList) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Authority.class)) {
                    Authority authority = field.getAnnotation(Authority.class);
                    HashMap<String, String> def = new HashMap<>();
                    def.put("code", authority.code());
                    def.put("name", authority.name());
                    def.put("category", authority.category());
                    def.put("description", authority.description());
                    defs.add(def);
                }
            }
        }

        return defs;
    }
}
