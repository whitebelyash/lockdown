package ru.whbex.lockdown.cmd;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {
    String name();
    String internalname();
    String description() default "Default command description";
    String usage() default "[args...] (default usage)";
    String permission();
    String parent() default "";
    String defaultCmd() default "";
    int minArgs() default 0;
    boolean requirePlayer() default false;
    boolean onlyConsole() default false;
}
