package com.example.cxfdemo.annotation;

import com.example.cxfdemo.entity.People;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 非空校验
 *
 * @author Sean
 * @date 2019/11/19
 */
public class AnnoUtils {

    public static void check(Object obj) throws Exception {
        List<String> msg = new ArrayList<>();
        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getAnnotations();
            for (Annotation anno : annotations) {
                Class<? extends Annotation> aClass1 = anno.annotationType();
                String className = aClass1.getName();
                Object o = field.get(obj);
                if (className.endsWith("NotNull")) {
                    NotNull notNull = (NotNull) anno;
                    if (o == null || "".equals(o.toString().trim())) {
                        msg.add(notNull.value());
                    }
                } else if (className.endsWith("Pattern")) {
                    Pattern pattern = (Pattern) anno;
                    String regexp = pattern.regexp();
                    java.util.regex.Pattern compile = java.util.regex.Pattern.compile(regexp);
                    if (o != null && !"".equals(o.toString().trim())) {
                        boolean matches = compile.matcher(o.toString()).matches();
                        if (!matches) {
                            msg.add(pattern.message());
                        }
                    }
                }

            }
        }
        if (msg.size() > 0) {
            throw new Exception(msg.toString());
        }
    }

    public static void main(String[] args) throws Exception {
        People people = new People();
        people.setName("sean");
        people.setPhone("110");
        check(people);
    }
}
