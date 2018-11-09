package jameel.banKChalo.customListeners;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UseAsTestRailId
{
public int testRailId() default 0;
public String[] tags() default "";
}