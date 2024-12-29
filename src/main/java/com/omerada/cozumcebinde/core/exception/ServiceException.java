package com.omerada.cozumcebinde.core.exception;

public class ServiceException extends RuntimeException {

    private String className;
    private String methodName;
    private Object fieldValue;

    public ServiceException(String message) {
        super(message);
    }
    public ServiceException( String className, String methodName, Object fieldValue,String message) {
        super(message);
        this.className = className;
        this.methodName = methodName;
        this.fieldValue = fieldValue;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }
}
