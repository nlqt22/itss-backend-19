package com.pinkieyun.fitnesscenter.utils;

import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import com.pinkieyun.fitnesscenter.exception.BusinessError;
import com.pinkieyun.fitnesscenter.exception.ICommonException;

@Component
public class HttpUtils {

    private final MessageSource messageSource;

    public HttpUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public BusinessError populateMessage(ICommonException ex, Locale locale) {
        if (locale == null) {
            // locale = new Locale("vi", "VI");
            locale = new Locale.Builder().setLanguage("vi").setRegion("VI").build();
        }
        BusinessError businessError = ex.getBusinessError();
        if (businessError != null) {

            String errorMessage = businessError.getErrorMessage();
            String errorCode = businessError.getErrorCode();
            Object params = businessError.getParams();

            if (errorMessage == null || errorMessage.isEmpty()) {
                businessError.errorMessage(
                    messageSource
                        .getMessage(errorCode, new Object[]{params}, "Has some errors!!", locale));
            }
            return businessError;
        } else {
            return null;
        }
    }

    public String getLanguage(WebRequest webRequest) {
        return  webRequest.getHeader("lang") != null ? webRequest.getHeader("lang") : "vi";
    };

    public String getLanguage(Map<String, String> headers) {
        return  headers.get("lang") != null ? headers.get("lang") : "vi";
    };

}