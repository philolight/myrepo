package com.lge.sm.cr_data_store.spring_config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.lge.framework.ceasar.event.load.LoadManager;
import com.lge.framework.ceasar.event.load.LoadType;
import com.lge.framework.pacific.logger.Logger;

@Aspect
public class AopTransactionException {
	private static final String TAG = AopTransactionException.class.getSimpleName();

    @Around("execution(* com.lge.sm.cr_data_store.anemics.adao..*.*(..)) || execution(* com.lge.sm.cr_data_store.dao..*.*(..))")
    public Object exceptionHandle(ProceedingJoinPoint joinpoint){

        Object obj = null;
        long start = System.currentTimeMillis();
        try {
            obj = joinpoint.proceed(); // run method
            String className = joinpoint.getSignature().getDeclaringTypeName();
            className = className.substring(className.lastIndexOf(".") + 1, className.length());
            String methodName = joinpoint.getSignature().getName();
            LoadManager.addLoad(className + "." + methodName, LoadType.DB_TRANSACTION, (System.currentTimeMillis() - start), 1);
            return obj;
        }
        catch (Throwable e) {
        	TransactionAspectSupport.currentTransactionStatus( ).setRollbackOnly(); // Transaction Rollback
      	
        	StackTraceElement[] elements = e.getStackTrace();
        	StringBuffer buf = new StringBuffer();
        	for(int i = elements.length - 1; i >= 0; i--) {
        		StackTraceElement each = elements[i];
        		if(each.getFileName() == null) continue;
        		if(each.getFileName().equals("<generated>")) break;
        		buf.append(each.getFileName() + " " + each.getLineNumber() + " / ");
        	}
        	
        	String message = (e.getCause() == null) ? e.getMessage() : e.getCause().getMessage();
        	Logger.error(TAG, "Transaction Failed : " + joinpoint.getSignature().getDeclaringTypeName() + "." + joinpoint.getSignature().getName() + "() - " + message);
        	Logger.error(TAG, "Transaction Failed : " + buf.toString());        
        	
        	Class<?> returnType = ((MethodSignature)joinpoint.getSignature()).getReturnType();
        	if(returnType.getName().equals("boolean")) return Boolean.FALSE;
        	else return null;
		}
    }
}