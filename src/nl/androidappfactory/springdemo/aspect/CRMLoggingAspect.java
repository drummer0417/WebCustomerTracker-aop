package nl.androidappfactory.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger logger = Logger.getLogger(getClass().getName());

	// setup pointcut declarations
	@Pointcut("execution(* nl.androidappfactory.springdemo.controller.*.*(..))")
	public void forControllerPackage() {}

	@Pointcut("execution(* nl.androidappfactory.springdemo.dao.*.*(..))")
	public void forDaoPackage() {}

	@Pointcut("execution(* nl.androidappfactory.springdemo.service.*.*(..))")
	public void forServicePackage() {}

	@Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
	public void combinedPackages() {}

	// add @Before advice
	@Before("combinedPackages()")
	public void beforeEachMethod(JoinPoint joinPoint) {

		String signature = joinPoint.getSignature().toShortString();
		logger.info("===========>>>>  @Before, signature: " + signature);

		Object[] args = joinPoint.getArgs();

		for (Object arg : args) {
			logger.info("=====----======>>>>  @Before, arg: " + arg);

		}
	}

	// add @AfterReturning advice

	@AfterReturning(pointcut = "combinedPackages()", returning = "result")

	public void afterReturning(JoinPoint joinPoint, Object result) {

		String signature = joinPoint.getSignature().toShortString();
		logger.info("===========>>>>  @AfterReturning, signature: " + signature);

		logger.info("===========>>>>  @AfterReturning, result: " + result);

	}
}
