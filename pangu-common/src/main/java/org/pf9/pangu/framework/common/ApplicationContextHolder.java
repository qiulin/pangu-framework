package org.pf9.pangu.framework.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationContextHolder implements ApplicationContextAware {

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextHolder.applicationContext = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void autowireBean(Object existingBean) {
		getApplicationContext().getAutowireCapableBeanFactory().autowireBean(existingBean);
	}

	public static Object getBean(String name) {
		Assert.hasText(name);
		return applicationContext.getBean(name);
	}

	public static <T> T getBean(Class<T> type) {
		Assert.notNull(type);
		return applicationContext.getBean(type);
	}

	public static <T> T getBean(String name, Class<T> type) {
		Assert.hasText(name, "must has text ");
		Assert.notNull(type, "type must not null");
		return applicationContext.getBean(name, type);
	}

	public static <T extends Annotation, F> List<F> findAnnotatedBeans(Class<T> annotationType, Class<F> elementType) {
		List<F> beans = new ArrayList<>();
		for (String name : BeanFactoryUtils.beanNamesForTypeIncludingAncestors(applicationContext, Object.class)) {
			if (applicationContext.findAnnotationOnBean(name, annotationType) != null) {
				beans.add(applicationContext.getBean(name, elementType));
			}
		}

		return beans;
	}

	public static <T> List<T> findBeansByType(Class<T> beanType) {

		return new ArrayList<>(applicationContext.getBeansOfType(beanType).values());
	}
}
