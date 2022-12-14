package pre045.board_service.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UsernameValidator.class)
public @interface UsernameNotDuplicate {
    String message() default "이미 존재하는 닉네임입니다. 다른 닉네임을 사용해주세요.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

