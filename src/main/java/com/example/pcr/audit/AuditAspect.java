package com.example.pcr.audit;

import com.example.pcr.service.AuditTrailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class AuditAspect {
    @Autowired
    private AuditTrailService auditTrailService;

    @AfterReturning("@annotation(auditable)")
    public void logAudit(JoinPoint joinPoint, Auditable auditable) {
        // Extract user details
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Extract action and entity details
        String action = auditable.action();
        String entityName = auditable.entityName();

        // Evaluate entityId expression
        String entityId = evaluateExpression(joinPoint, auditable.entityIdExpression());

        // Build details string
        String details = Arrays.toString(joinPoint.getArgs());

        // Log the audit trail
        auditTrailService.logAction(username, action, entityName, entityId, details);
    }

    private String evaluateExpression(JoinPoint joinPoint, String expression) {
        if (expression.isEmpty()) {
            return "";
        }

        try {
            ExpressionParser parser = new SpelExpressionParser();
            StandardEvaluationContext context = new StandardEvaluationContext();
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Object[] args = joinPoint.getArgs();
            String[] parameterNames = signature.getParameterNames();

            for (int i = 0; i < parameterNames.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }

            String result = parser.parseExpression(expression).getValue(context, String.class);
            System.out.println("Evaluated expression: " + expression + " -> " + result);
            return result;
        } catch (Exception e) {
            System.err.println("Error evaluating expression: " + expression);
            e.printStackTrace();
            return "";
        }
    }
}
