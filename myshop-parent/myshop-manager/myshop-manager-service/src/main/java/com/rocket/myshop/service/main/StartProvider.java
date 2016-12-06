
package com.rocket.myshop.service.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
* @FullClassName com.rocket.myshop.service.main.StartProvider
* @Description: TODO
* @author  Liu Jie
* @date 2016年11月24日 下午2:12:21 
* @version V1.0.0
 */
public class StartProvider {
	
	private static volatile boolean running = true;

	@SuppressWarnings("all")
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext(new String[] { "service-initconfig.xml" });
		context.start();
		System.out.println("dubbo service provider started...");
		synchronized (StartProvider.class) {
			while (running) {
				try {
					StartProvider.class.wait();
				} catch (Throwable e) {
				}
			}
		}
	}
}
