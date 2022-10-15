package by.evgen.cafe.aspect;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Log4j2
@Component
public class MainPageControllerAspect {

    @Pointcut("@within(by.evgen.cafe.aspect.LoggingMainPage) && execution(* mainPage(..))")
    public void beforeMainPage(){}

    @Before("beforeMainPage()")
    public void loggingBeforeMainPageAdvice() {
        log.info("Trying to get the main page of the application");
    }
}
