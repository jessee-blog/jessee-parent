package com.jessee.framwork;

import ch.qos.logback.classic.Logger;
import com.alibaba.fastjson.JSONObject;
import com.jessee.framwork.annotition.HPResponseBody;
import com.jessee.framwork.context.HPApplitionContext;
import com.jessee.framwork.context.HPHandler;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by jessee on 2018/12/15.
 */
public class HPDispatchServlet extends HttpServlet {

    Logger logger = (Logger) LoggerFactory.getLogger(HPDispatchServlet.class);

    private HPApplitionContext hpApplitionContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        String configLocation = config.getInitParameter("configLocation");
        hpApplitionContext = new HPApplitionContext(configLocation);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doPost...");
        Map<String, HPHandler> hpHandlerMap = hpApplitionContext.getHandlerMapping();
        logger.info("");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("doGet...");
        Map<String, HPHandler> hpHandlerMap = hpApplitionContext.getHandlerMapping();
        String equestURI = req.getRequestURI();
        if (!hpHandlerMap.containsKey(equestURI)) {
            PrintWriter writer = resp.getWriter();
            writer.write("not found this uri");
            return;
        }
        HPHandler hpHandler = hpHandlerMap.get(equestURI);
        Method method = hpHandler.getMethod();
        try {
            Map<String, String[]> map = req.getParameterMap();
            Object[] requestParams = new Object[]{map.size()};
            Map<String, Integer> methodParams = hpHandler.getParamsMap();
            Class<?>[] types = method.getParameterTypes();
            //参数传递
            for (Map.Entry<String, String[]> entry : map.entrySet()) {
                String key = entry.getKey();
                if (!methodParams.containsKey(key)) return;
                String value = Arrays.toString(entry.getValue()).replaceAll("\\]|\\[", "").replaceAll(",\\s", ",");


                int position = methodParams.get(key);

                requestParams[position] = typeChange(types[position], value);

                System.out.println(key + "==>" + value);
            }
            /*int index = methodParams.get(HttpServletRequest.class.getName());
            requestParams[index] = req;
            int index2 = methodParams.get(HttpServletResponse.class.getName());
            requestParams[index2] = resp;*/

            Object res = method.invoke(hpHandler.getController(), requestParams);

            if (method.isAnnotationPresent(HPResponseBody.class)) {
                res = JSONObject.toJSONString(res);
            }
            System.out.println("res==>" + res.toString());

            Writer writer = resp.getWriter();
            writer.write(res.toString());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        logger.info("getRequestURI:{}", req.getRequestURI());
    }

    private Object typeChange(Class<?> type, String value) {
        if (type == String.class) {
            return value;
        }
        if (type == Integer.class) {
            return Integer.valueOf(value);
        }
        if (type == int.class) {
            return Integer.valueOf(value).intValue();
        }
        throw new RuntimeException("不支持的参数类型");
    }
}
