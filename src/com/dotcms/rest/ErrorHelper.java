package com.dotcms.rest;

import com.dotcms.repackage.javax.ws.rs.core.Response;
import com.liferay.portal.language.LanguageException;
import com.liferay.portal.language.LanguageUtil;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Locale;

/**
 * Generic error helper for rest stuff
 * @author jsanca
 */
public class ErrorHelper implements Serializable {

    public final static ErrorHelper INSTANCE = new ErrorHelper();

    private ErrorHelper() {}

    /**
     * Get Error response based on a status and message key
     * This support is a single message
     *
     * @param status
     * @param messageKey
     * @return Response
     */
    public Response getErrorResponse(final Response.Status status,
                                     final Locale locale,
                                     final String messageKey) {

        try {

            return Response.status(status).entity
                    (new ResponseEntityView
                            (Arrays.asList(new ErrorEntity(messageKey,
                                    LanguageUtil.get(locale,
                                            messageKey))))).build();


        } catch (LanguageException e1) {
            // Quiet
        }

        return null;
    }
} // E:O:F:ErrorHelper.
