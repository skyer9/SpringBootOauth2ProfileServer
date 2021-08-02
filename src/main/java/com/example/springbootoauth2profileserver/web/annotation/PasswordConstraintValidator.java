package com.example.springbootoauth2profileserver.web.annotation;

import com.google.common.base.Joiner;
import org.passay.*;
import org.passay.spring.SpringMessageResolver;
import org.springframework.context.support.StaticMessageSource;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Locale;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        final StaticMessageSource messageSource = new StaticMessageSource();

        messageSource.addMessage("TOO_SHORT", Locale.KOREA, "비밀번호는 {0}-{1} 자리여야 합니다.");
        messageSource.addMessage("INSUFFICIENT_LOWERCASE", Locale.KOREA, "비밀번호에 최소 {0} 개 이상의 소문자가 있어야 합니다.");
        messageSource.addMessage("INSUFFICIENT_DIGIT", Locale.KOREA, "비밀번호에 최소 {0} 개 이상의 숫자가 있어야 합니다.");
        messageSource.addMessage("INSUFFICIENT_SPECIAL", Locale.KOREA, "비밀번호에 최소 {0} 개 이상의 특수문자가 있어야 합니다.");

        PasswordValidator validator = new PasswordValidator(
                new SpringMessageResolver(messageSource, Locale.KOREA),
                Arrays.asList(
                        new LengthRule(8, 30),
                        // new CharacterRule(EnglishCharacterData.UpperCase, 1),
                        new CharacterRule(EnglishCharacterData.LowerCase, 1),
                        new CharacterRule(EnglishCharacterData.Digit, 1),
                        new CharacterRule(EnglishCharacterData.Special, 1),
                        new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
                        new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false),
                        new IllegalSequenceRule(EnglishSequenceData.USQwerty, 5, false),
                        new WhitespaceRule()));

        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                        Joiner.on(",").join(validator.getMessages(result)))
                .addConstraintViolation();
        return false;
    }
}
