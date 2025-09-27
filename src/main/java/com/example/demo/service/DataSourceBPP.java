package com.example.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.Method;

@Component
@Slf4j
public class DataSourceBPP implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DataSource source) {
            final ProxyFactory factory = new ProxyFactory(bean);
            factory.setProxyTargetClass(true);
            factory.addAdvice(new ProxyDataSourceInterceptor(source));
            return factory.getProxy();
        }
        return bean;
    }

    @RequiredArgsConstructor
    private static class ProxyDataSourceInterceptor implements MethodInterceptor {
        private final DataSource dataSource;
        @Override
        public Object invoke(final MethodInvocation invocation) throws Throwable {
            final Method method = ReflectionUtils.findMethod(
                    this.dataSource.getClass(),
                    invocation.getMethod().getName()
            );
            if (method != null && method.getName().equals("getConnection")) {
                log.warn("DataSource connection acquired");
            }
            return invocation.proceed();
        }
    }
}

