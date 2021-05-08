package week1.jvm;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by liaock on 2021/5/1
 **/
public class Client {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, UnsupportedEncodingException {
        URL url = Client.class.getClassLoader().getResource("Hello.xlass");
        URLDecoder decoder = new URLDecoder();
        String path = decoder.decode(url.getPath(),"utf-8");
        CustomizedClassLoader loader = new CustomizedClassLoader(path);
        Class<?> clazz = loader.findClass("Hello");
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("hello");
        method.invoke(obj);
    }
}
