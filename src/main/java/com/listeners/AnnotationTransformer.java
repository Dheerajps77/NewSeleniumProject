package com.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        // Check if a Retry Analyzer class is already assigned
        Class<? extends IRetryAnalyzer> retryAnalyzerClass = annotation.getRetryAnalyzerClass();

        // If no retry analyzer class is set, assign our custom Retry class
        if (retryAnalyzerClass == null) {
            annotation.setRetryAnalyzer(Retry.class);
        }
    }
}
