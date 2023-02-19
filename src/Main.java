import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        Map<Class<?>, Integer> map = new HashMap<Class<?>, Integer>();
        map = analyze(Position.class, MetodAnnotation.class, ParameterAnnotation.class);

        Map<Class<?>, Integer> map2 = new HashMap<Class<?>, Integer>();
        map2 = analizeRecursion(Position.class, MetodAnnotation.class, ParameterAnnotation.class, map2);

        Map<Class<?>, Integer> map3 = new HashMap<Class<?>, Integer>();
        map3.put(MetodAnnotation.class, 0);
        map3.put(ParameterAnnotation.class,0);
        map3 = analizeRecursionOneMap(Position.class, MetodAnnotation.class, ParameterAnnotation.class, map3);

        Map<Class<?>, Integer> map4 = new HashMap<Class<?>, Integer>();
        map4 = analizeRecursionOneEmptyMap(Position.class, MetodAnnotation.class, ParameterAnnotation.class, map4);

        System.out.println(map.toString());
        System.out.println(map2.toString());
        System.out.println(map3.toString());
        System.out.println(map4.toString());
    }

    public static Map<Class<?>, Integer> analyze (Class<?> cls, Class<? extends Annotation> metodAn, Class<? extends Annotation> paramAn) {

        Map<Class<?>, Integer> map = new HashMap<Class<?>, Integer>();
        Integer a = 0;
        Integer b = 0;

        for(; !cls.equals(Object.class); ) {
            Method[] methods = cls.getDeclaredMethods();
            for (Method method: methods) {
                if(method.isAnnotationPresent(metodAn)) {
                    a = a + 1;
                    map.put(metodAn, a);
                }
                Parameter[] parameters = method.getParameters();
                for(Parameter parameter: parameters) {
                    if(parameter.isAnnotationPresent(paramAn)){
                        b = b + 1;
                        map.put(paramAn, b);
                    }
                }
            }

            cls = cls.getSuperclass();
        }
        return map;
    }

    public static Map<Class<?>, Integer> analizeRecursion (Class<?> cls, Class<? extends Annotation> metodAn,
                                                           Class<? extends Annotation> paramAn,
                                                           Map<Class<?>, Integer> mapOld) {
        Map<Class<?>, Integer> map = new HashMap<Class<?>, Integer>();

        if(!cls.equals(Object.class)){
            Map<Class<?>, Integer> mapNew = new HashMap<Class<?>, Integer>();
            Integer a = 0;
            Integer b = 0;
            Method[] methods = cls.getDeclaredMethods();
            for (Method method: methods) {
                if(method.isAnnotationPresent(metodAn)) {
                    a = a + 1;
                    mapNew.put(metodAn, a);
                }
                Parameter[] parameters = method.getParameters();
                for(Parameter parameter: parameters) {
                    if(parameter.isAnnotationPresent(paramAn)){
                        b = b + 1;
                        mapNew.put(paramAn, b);
                    }
                }
            }

            map = Stream.of(mapOld, mapNew)
                    .flatMap(m -> m.entrySet().stream())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue + newValue));
            cls = cls.getSuperclass();
            mapOld = analizeRecursion (cls, MetodAnnotation.class, ParameterAnnotation.class,
                    map);
        }
        return mapOld;
    }

    public static Map<Class<?>, Integer> analizeRecursionOneMap (Class<?> cls, Class<? extends Annotation> metodAn,
                                                                     Class<? extends Annotation> paramAn,
                                                                     Map<Class<?>, Integer> mapOld) {

        if(!cls.equals(Object.class)){
            Method[] methods = cls.getDeclaredMethods();
            for (Method method: methods) {
                if(method.isAnnotationPresent(metodAn)) {
                    mapOld.compute(metodAn,(key, value) -> value = value + 1);

                }
                Parameter[] parameters = method.getParameters();
                for(Parameter parameter: parameters) {
                    if(parameter.isAnnotationPresent(paramAn)){
                        mapOld.compute(paramAn,(key, value) -> value = value + 1);
                    }
                }
            }
            cls = cls.getSuperclass();
            mapOld = analizeRecursion (cls, MetodAnnotation.class, ParameterAnnotation.class,
                    mapOld);

        }
        return mapOld;
    }

    public static Map<Class<?>, Integer> analizeRecursionOneEmptyMap (Class<?> cls, Class<? extends Annotation> metodAn,
                                                                 Class<? extends Annotation> paramAn,
                                                                 Map<Class<?>, Integer> mapOld) {

        if(!cls.equals(Object.class)){
            Method[] methods = cls.getDeclaredMethods();
            for (Method method: methods) {
                if(method.isAnnotationPresent(metodAn)) {
                    mapOld.computeIfAbsent(metodAn, (key) -> 0);
                    mapOld.computeIfPresent(metodAn, (key, value) -> value = value + 1);
                }
                Parameter[] parameters = method.getParameters();
                for(Parameter parameter: parameters) {
                    if(parameter.isAnnotationPresent(paramAn)){
                        mapOld.computeIfAbsent(paramAn, (key) -> 0);
                        mapOld.computeIfPresent(paramAn,(key, value) -> value = value + 1);
                    }
                }
            }
            cls = cls.getSuperclass();
            mapOld = analizeRecursion (cls, MetodAnnotation.class, ParameterAnnotation.class,
                    mapOld);
        }
        return mapOld;
    }

}