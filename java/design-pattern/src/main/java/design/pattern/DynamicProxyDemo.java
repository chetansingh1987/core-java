package design.pattern;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class DynamicProxyDemo {
    public static void main(String[] args) {
        Human p =  withlogging(new Person(),Human.class);
        p.talk();
        p.talk();
        p.walk();
        System.out.println(p);

    }

    public static <T> T withlogging(Object target,Class<T> intf){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),new Class[]{intf},new LoggingHandler(target));
    }
}

interface Human {
    void walk();
    void talk();
}


class LoggingHandler implements InvocationHandler {
    private final Object target;
    Map<String,Integer> calls = new HashMap<>();
    LoggingHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String name = method.getName();
        if(name.contains("toString")){
            return calls.toString();
        }
        calls.merge(method.getName(),1,Integer::sum);
        method.invoke(target,args);
        return null;
    }
}

class Person implements Human {
    @Override
    public void walk() {
        System.out.println("I am walking");
    }

    @Override
    public void talk() {
        System.out.println("I am talking");
    }
}
