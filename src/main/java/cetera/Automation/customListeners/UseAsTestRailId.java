package cetera.Automation.customListeners;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UseAsTestRailId
{
public String[] testRailId() default "";
public String[] tags() default "";
}