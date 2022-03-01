/**
 * 
 */
package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

/**
 * @author Abhishek
 *
 */

@Aspect
@Component
public class CRMLoggingAspect {

	//setup logger
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	//setup pointcut declaration
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	public void forControllerPackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	public void forServicePackage() {}
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	public void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() ||forServicePackage() ||forDaoPackage()")
	public void forAppFlow() {}
	
	
	//add @Before advice
	
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		//display method name we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("===>calling method : "+theMethod);
		
		//display the arguments
		Object[] args = theJoinPoint.getArgs();
		
		for(Object arg : args) {
			myLogger.info("===>arguments : "+arg);
		}
	}
	
	@AfterReturning(pointcut="forAppFlow()",
			returning="result")
	public void after(JoinPoint theJoinPoint, Object result) {
		//display the method we are returning from
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("===>after returning from method : "+theMethod);
		
		//display the data
		myLogger.info("===>result : "+result);
	}
	
	//add @AfterReturning advice
	
}
