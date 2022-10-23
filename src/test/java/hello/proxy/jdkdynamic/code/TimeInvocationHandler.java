package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

// 동적으로 생성하려면 InvocationHandler 를 구현해서 기능을 넣은 클래스가 필요
@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        log.info("TimeProxy 실행");
        long startTime = System.currentTimeMillis();

        //method 사용하여 target(클래스)를 넣고 해당 메소드를 실행 시킴, objects는 메소드 호출 시 넘겨주는 인수
        Object result = method.invoke(target, objects); //target 클래스에 call 이 넘어가면서 실행하게되는거임

        long endTime = System.currentTimeMillis();
        long resultTime = endTime-startTime;
        log.info("TimeProxy 종료 resultTime={}",resultTime);

        return result;
    }
}
