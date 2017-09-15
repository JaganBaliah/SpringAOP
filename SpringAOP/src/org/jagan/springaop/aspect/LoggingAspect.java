package org.jagan.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

	//@Before("execution(public String getName())")
	@Before("execution(public String org.jagan.springaop.model.Circle.getName())")
	//@Before("execution(public String org.jagan.springaop.model.Triangle.getName())")
	//@Before("execution(public String org.jagan.springaop.model.*.getName())")
	//@Before("execution(public String get*())")
	//@Before("execution(public * get*())")
	//@Before("execution(* get*())")
	//@Before("execution(* get*(*))")   //Requires atleast one argument
	//@Before("execution(* get*(..))")	//Requires zero or more arguments
	//@Before("allGetters()")
	public void LoggingAdvice() {
		System.out.println("Advice run. Get Method called");
	}
	
	@Before("allGetters() && allCircleMethods()")
	public void secondAdvice() {
		System.out.println("Second Advice executed.");
	}
	
	@Pointcut("execution(* get*(..))")
	public void allGetters(){}
		
	@Before("allCircleMethods()")
	public void thirdAdvice(JoinPoint joinPoint) {
		if(joinPoint.getSignature().getName() == "getName") {
			//System.out.println(joinPoint);
			//System.out.println(joinPoint.getTarget());
			//Circle circle = (Circle)joinPoint.getTarget();
			//System.out.println("Circle : " + circle);
			System.out.println("Circle Advice executed for getter");
		} else if(joinPoint.getSignature().getName() == "setName") {
			System.out.println("Circle Advice executed for setter");
		} else {
			System.out.println("Circle Advice executed.");
		}		
	}
	
	//@Pointcut("execution(* org.jagan.springaop.model.Circle.*(..))")
	@Pointcut("within(org.jagan.springaop.model.Circle)")
	//@Pointcut("within(org.jagan.springaop.model.*)")
	//@Pointcut("within(org.jagan.springaop.model..*)") //all sub packages within model
	public void allCircleMethods(){}
		
	
	//@Before("args(String)")
	//public void stringArgumentMethods() {
	
	@Before("args(name)")
	public void stringArgumentMethods(String name) {
		System.out.println("A method that takes String argument has been called. The value is " + name);
	}
	
	/*
	@After("args(name)")
	public void stringArgumentMethodsAfter(String name) {
		System.out.println("A method that takes String argument has been executed. The value is " + name);
	}
	
	@AfterReturning("args(name)")
	public void stringArgumentMethodsAfterReturning(String name) {
		System.out.println("A method that takes String argument has been executed. The value is " + name);
	}
	
	@AfterThrowing("args(name)")
	public void exceptionAdvice(String name) {
		System.out.println("An execption has been thrown");
	}
	*/
	
	@AfterReturning(pointcut="args(name)", returning="returnString")
	//public void stringArgumentMethodsAfterReturning(String name, String returnString) {
	public void stringArgumentMethodsAfterReturning(String name, Object returnString) {
		System.out.println("A method that takes String argument has been executed. The value is " + name + " and returnString is " + returnString);
	}
	
	@Around("allGetters()")
	public Object myAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		
		Object rtnVal = null;
		try{
			System.out.println("Before Advice");
			rtnVal = proceedingJoinPoint.proceed();
			System.out.println("AFter Advice");
			
		} catch(Throwable e) {
			System.out.println("After Throwing");
		}
		System.out.println("After Finally");
		return rtnVal;
	}
	
}
