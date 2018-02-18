package com.lge.sm.cr_data_store.transaction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.lge.framework.ceasar.logger.Logger;

@Aspect
public class TestAopTransactionException {
	private static final String TAG = TestAopTransactionException.class.getSimpleName();
	
	@Around("execution(* com.lge.sm.cr_data_store.transaction..*.*(..))")
	 // or execution(* com.lge.sm.cr_data_store.dao..*.*(..))
    public Object exceptionHandle(ProceedingJoinPoint joinpoint){	
        Object obj = null;

        try {
            obj = joinpoint.proceed(); // run method
            return obj;
        }
        catch (Throwable e) {
        	TransactionAspectSupport.currentTransactionStatus( ).setRollbackOnly(); // Transaction Rollback
        	
        	String message = (e.getCause() == null) ? e.getMessage() : e.getCause().getMessage();
        	Logger.error(TAG, "Transaction Failed : " + message);
        	
        	Class<?> returnType = ((MethodSignature)joinpoint.getSignature()).getReturnType();
        	if(returnType.getName().equals("boolean")) return Boolean.FALSE;
        	else return null;
		}
    }
}