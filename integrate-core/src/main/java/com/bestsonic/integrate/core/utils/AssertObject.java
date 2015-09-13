package com.bestsonic.integrate.core.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

public class AssertObject {

	public static <T> void assertObject(Class<T> clazz, T base, T actual){
		assertNotNull(actual);
		try {
			BeanInfo info = Introspector.getBeanInfo(clazz, Object.class);
			PropertyDescriptor[] descriptors = info.getPropertyDescriptors();
			for(PropertyDescriptor pd : descriptors){
				assertEquals(pd.getReadMethod().invoke(base, (Object[])null), pd.getReadMethod().invoke(actual, (Object[])null));
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
