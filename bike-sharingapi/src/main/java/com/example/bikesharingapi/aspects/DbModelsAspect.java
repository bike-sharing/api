package com.example.bikesharingapi.aspects;

import com.example.bikesharingapi.filters.AuthenticationFilter;
import com.example.bikesharingapi.models.Bicycle;
import com.example.bikesharingapi.models.Location;
import com.example.bikesharingapi.models.State;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.hibernate.mapping.Join;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DbModelsAspect {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Before(value = "execution(* com.example.bikesharingapi.controllers.*.*(..)) && args(object)")
    public void beforeAdvice(JoinPoint joinPoint, Object object) {
        LOG.info("Before the execution of method: " + joinPoint.getSignature());
        if(object instanceof Bicycle) {
            LOG.info("Creating bicycle with id: " + ((Bicycle) object).getBicycleId());
        }
        else if(object instanceof Location) {
            LOG.info("Creating location with id: " + ((Location) object).getLocationId());
        } else if (object instanceof State) {
            LOG.info("Creating state with id: " + ((State) object).getStateId());
        }

    }

    @After(value = "execution(* com.example.bikesharingapi.controllers.*.*(..)) && args(object)")
    public void afterAdvice(JoinPoint joinPoint, Object object) {
        LOG.info("After the execution of method: " + joinPoint.getSignature());
        if(object instanceof Bicycle) {
            LOG.info("Successfully created bicycle with id: " + ((Bicycle) object).getBicycleId());
        }
        else if(object instanceof Location) {
            LOG.info("Successfully created location with id: " + ((Location) object).getLocationId());
        } else if (object instanceof State) {
            LOG.info("Successfully created state with id: " + ((State) object).getStateId());
        }

    }

    @AfterThrowing("within(com.example.bikesharingapi.controllers.BicycleController)")
    public void logException(JoinPoint joinPoint) {
        LOG.error("Exception thrown in Bicycle method = " + joinPoint.getSignature());
    }

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void logTransactional(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        LOG.info("(Transactional) Before method: " + methodName);
        LOG.info("The arguments of the (transactional) method are: ");
        for(var argument : joinPoint.getArgs()) {
            LOG.info("Argument: " + argument);
        }
    }

    @Before("@annotation(org.springframework.web.bind.annotation.DeleteMapping)")
    public void logRequestsWithDelete(JoinPoint joinPoint){
        LOG.warn("DELETE request with method: " + joinPoint.getSignature());
        LOG.info("The arguments of the method called by the request: ");
        for(var argument : joinPoint.getArgs()) {
            LOG.info("Argument: " + argument);
        }
    }

    @Before("@annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void logRequestsWithPut(JoinPoint joinPoint){
        LOG.warn("PUT request with method: " + joinPoint.getSignature());
        LOG.info("The arguments of the method called by the request: ");
        for(var argument : joinPoint.getArgs()) {
            LOG.info("Argument: " + argument);
        }
    }

    @AfterReturning(value = "execution(* com.example.bikesharingapi.controllers.BicycleController.borrowBicycle(*)) || " +
            "execution(* com.example.bikesharingapi.controllers.BicycleController.borrowBicycle(*))", returning = "returnValue")
    public void log(JoinPoint joinPoint, Object returnValue) {
        if(returnValue != null) {
            LOG.info("Return value of the method: " + joinPoint.getSignature() + " = " + returnValue.toString());
        }
        else {
            LOG.info("Method: " + joinPoint.getSignature() + " did not return any values!");
        }
    }
}
