package com.dotcms.exception;

import com.dotmarketing.util.Logger;
import com.liferay.portal.language.LanguageUtil;
import com.liferay.portal.model.User;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Exception Utils
 * @author andrecurione
 */
public class ExceptionUtil {

    private ExceptionUtil () {}

    /**
     * Returns true if the Throwable is instance or contains a cause of the specified ExceptionClass
     * @param e
     * @param exceptionClass
     * @return boolean
     */
    public static boolean causedBy(final Throwable e, final Class exceptionClass) {

        Throwable t = e;
        while (t != null) {
            if (t.getClass().equals(exceptionClass)) {
                return true;
            }
            t = t.getCause();
        }
        return false;
    }

    /**
     * Returns true if the Throwable is instance or contains a cause of the specified on any of the exceptionClasses
     * @param e
     * @param exceptionClasses
     * @return boolean
     */
    public static boolean causedBy(final Throwable e, final Class... exceptionClasses) {

        return causedBy(e, new HashSet<>(Arrays.asList(exceptionClasses)));
    }

    /**
     * Returns true if the Throwable is instance or contains a cause of the specified on any of the exceptionClasses
     * @param e
     * @param exceptionClasses
     * @return boolean
     */
    public static boolean causedBy(final Throwable e, final Set<Class> exceptionClasses) {

        Throwable t = e;

        while (t != null) {

            if (exceptionClasses.contains(t.getClass())) {
                return true;
            }

            t = t.getCause();
        }

        return false;
    }

    public static String getLocalizedMessageOrDefault(final User user, final String key, final String defaultMessage, final Class clazz){
        String message = defaultMessage;
        try {
            message = LanguageUtil.get(user, key);
        }catch(Exception e){
            if(clazz != null){
              Logger.error(clazz, e.toString());
            }
        }
        return message;
    }

}