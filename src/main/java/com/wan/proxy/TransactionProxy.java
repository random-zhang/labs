package com.wan.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.wan.exception.ServiceException;
import com.wan.factory.ObjectFactory;
import com.wan.transaction.TransManagerI;

public class TransactionProxy {
	private static TransManagerI transManager = (TransManagerI) ObjectFactory.getObject("iTransaction");

	// 被代理类

	public static Object createProxy(final Object target) {

		// 通过JDK创建的动态代理
		Object proxyObject = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),
				new InvocationHandler() {

					@Override
					public Object invoke(Object proxy, Method method,Object[] args) throws Throwable {
						Object res = null;
						try {
							transManager.beginTrans();
							res = method.invoke(target, args);
							transManager.commitTrans();
						} catch (Exception e) {
							transManager.rollbackTrans();
							throw new ServiceException(e);
						}

						return res;
					}
				});
		return proxyObject;
	}

}
