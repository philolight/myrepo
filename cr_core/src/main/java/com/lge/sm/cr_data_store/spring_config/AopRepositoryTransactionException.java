package com.lge.sm.cr_data_store.spring_config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.lge.framework.ceasar.event.load.LoadManager;
import com.lge.framework.ceasar.event.load.LoadType;
import com.lge.framework.ceasar.logger.Logger;

@Aspect
public class AopRepositoryTransactionException {
	public AopRepositoryTransactionException() {
		System.out.println("AopRepositoryTransactionException()");
	}
	
	private static final String TAG = AopRepositoryTransactionException.class.getSimpleName();

    @Around("execution(public * com.lge.sm.cr_data_store.anemics.arepository..*.*(..)) || execution(public * com.lge.sm.cr_data_store.repository..*.*(..))")
    public Object method(ProceedingJoinPoint joinpoint){

//    	System.out.println("deleteDao");
    	
        Object obj = null;
        long start = System.currentTimeMillis();
        try {
            String className = joinpoint.getSignature().getDeclaringTypeName();
//System.out.println(className + "." + joinpoint.getSignature().getName());
            className = className.substring(className.lastIndexOf(".") + 1, className.length());
            String methodName = joinpoint.getSignature().getName();
        	
            obj = joinpoint.proceed(); // run method
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