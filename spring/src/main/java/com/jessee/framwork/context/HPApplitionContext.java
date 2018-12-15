package com.jessee.framwork.context;

import com.jessee.framwork.annotition.*;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jessee on 2018/12/15.
 */
public class HPApplitionContext {

    //根据路径扫描class、放在clazzMap中,key为类开头字母小写、value为权限命名
    //将clazzMap中的beans实例化，封装到BeanDefinition、放入beansMap中
    //将beansMap中的bean需要的对象注入

    private String configLocation;
    private List<String> classNames = new ArrayList<String>();
    private Map<String, Object> instanceMapping = new HashMap<String, Object>();
    private Map<String, HPHandler> handlerMapping = new HashMap<String, HPHandler>();

    public HPApplitionContext(String configLocation) {
        this.configLocation = configLocation;
        init();
    }

    public void init() {
        scanClass(configLocation);
        instance();
        autowired();
        //请求路径和controller映射
        handlerMapping();
    }

    private void handlerMapping() {
        if (instanceMapping.size() == 0) return;
        for (Map.Entry<String, Object> entry : instanceMapping.entrySet()) {
            if (entry.getValue().getClass().isAnnotationPresent(HPRequestMapping.class)) {
                HPRequestMapping hpRequestMappingParent = entry.getValue().getClass().getAnnotation(HPRequestMapping.class);
                String parentUrl = hpRequestMappingParent.value();
                Method[] methods = entry.getValue().getClass().getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(HPRequestMapping.class)) {
                        HPRequestMapping hpRequestMapping = method.getAnnotation(HPRequestMapping.class);
                        String url = (parentUrl + hpRequestMapping.value()).replaceAll("/+", "/");

                        //参数解析
                        Map<String, Integer> paramsMap = new HashMap<>();
                        Annotation[][] annotations = method.getParameterAnnotations();
                        for (Annotation[] annotations1 : annotations) {
                            for (int i = 0; i < annotations1.length; i++) {
                                Annotation annotation = annotations1[i];
                                if (annotation instanceof HPRequestParam) {
                                    String parmaName = ((HPRequestParam) annotation).value();
                                    paramsMap.put(parmaName, i);
                                }
                            }
                        }

                        HPHandler handler = new HPHandler(entry.getValue(), method, paramsMap);
                        handlerMapping.put(url, handler);
                        System.out.println("url:" + url + "==>" + method.getName());
                    }
                }
            }
        }
    }

    private void autowired() {
        if (instanceMapping.size() == 0) return;
        for (Map.Entry<String, Object> entry : instanceMapping.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(HPAutowire.class)) {
                    HPAutowire hpAutowire = field.getAnnotation(HPAutowire.class);
                    String beanName = hpAutowire.value();
                    if ("".equals(beanName)) {
                        beanName = field.getType().getName();
                    }
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    try {
                        field.set(entry.getValue(), instanceMapping.get(beanName));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void instance() {
        if (classNames.isEmpty()) {
            return;
        }
        for (String className : classNames) {
            try {
                Class clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(HPController.class)) {
                    String beanName = lowerFirstChar(clazz.getSimpleName());
                    instanceMapping.put(beanName, clazz.newInstance());
                } else if (clazz.isAnnotationPresent(HPService.class)) {
                    HPService hpService = (HPService) clazz.getAnnotation(HPService.class);
                    if ("".equals(hpService.value().trim())) {
                        Class<?>[] interfaces = clazz.getInterfaces();
                        for (Class<?> i : interfaces) {
                            instanceMapping.put(i.getName(), clazz.newInstance());
                        }
                    } else {
                        instanceMapping.put(hpService.value(), clazz.newInstance());
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    private String lowerFirstChar(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void scanClass(String packageName) {

        //拿到包路径，转换为文件路径
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));

        File dir = new File(url.getFile());

        //递归查找到所有的class文件
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                scanClass(packageName + "." + file.getName());
            } else {
                //.class
                String className = packageName + "." + file.getName().replace(".class", "");
                classNames.add(className);
            }
        }

    }

    public Map<String, HPHandler> getHandlerMapping() {
        return handlerMapping;
    }
}
