package jameel.banKChalo.customListeners;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UseAsTestCaseId
{
public int testCaseId() default 0;
public String[] tags() default "";
}