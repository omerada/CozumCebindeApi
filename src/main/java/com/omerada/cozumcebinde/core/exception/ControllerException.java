package com.omerada.cozumcebinde.core.exception;

public class ControllerException extends  RuntimeException {

    private String className;
    private String methodName;
    private String exceptionCause;

    public ControllerException( String className, String methodName,String exceptionCause) {
        super(String.format("ControllerException : %s - %s : '%s'", className, methodName, exceptionCause));
        this.className = className;
        this.methodName = methodName;
        this.exceptionCause = exceptionCause;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getExceptionCause() {
        return exceptionCause;
    }
}
