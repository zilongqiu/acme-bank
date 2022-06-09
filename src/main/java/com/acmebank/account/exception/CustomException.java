package com.acmebank.account.exception;

import com.acmebank.account.config.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

@Component
public class CustomException {

    private static PropertiesConfig propertiesConfig;

    @Autowired
    public CustomException(PropertiesConfig propertiesConfig) {
        CustomException.propertiesConfig = propertiesConfig;
    }

    /**
     * Returns new RuntimeException based on template and args
     */
    public static RuntimeException throwException(String messageTemplate, String... args) {
        return new RuntimeException(format(messageTemplate, args));
    }

    /**
     * Returns new RuntimeException based on EntityType, ExceptionType and args
     */
    public static RuntimeException throwException(EntityType entityType, ExceptionType exceptionType, String... args) {
        String messageTemplate = getMessageTemplate(entityType, exceptionType);
        return throwException(exceptionType, messageTemplate, args);
    }

    private static RuntimeException throwException(ExceptionType exceptionType, String messageTemplate, String... args) {
        if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
            return new EntityNotFoundException(format(messageTemplate, args));
        } else if (ExceptionType.ENTITY_BALANCE_INVALID.equals(exceptionType)) {
            return new EntityBadRequestException(format(messageTemplate, args));
        }
        return new RuntimeException(format(messageTemplate, args));
    }

    private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
        return entityType.name().concat(".").concat(exceptionType.getValue()).toLowerCase();
    }

    private static String format(String template, String ... args) {
        Optional<String> templateContent = Optional.ofNullable(propertiesConfig.getConfigValue(template));
        if (templateContent.isPresent()) {
            return MessageFormat.format(templateContent.get(), (Object[]) args);
        }
        return String.format(template, (Object[]) args);
    }

    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class EntityBadRequestException extends RuntimeException {
        public EntityBadRequestException(String message) {
            super(message);
        }
    }
}
