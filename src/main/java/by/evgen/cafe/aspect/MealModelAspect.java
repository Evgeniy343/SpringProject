package by.evgen.cafe.aspect;


import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Log4j2
@Component
public class MealModelAspect {

    @Pointcut("@within(by.evgen.cafe.aspect.LoggingMealModel) && execution(* by.evgen.cafe.model.*.getImage())")
    public void loggingBeforeGetImage(){}

    @Before("loggingBeforeGetImage()")
    public void beforeGetImageAdvice() {
        log.info("Trying to get the image of the dish");
    }
}
