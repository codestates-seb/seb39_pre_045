package pre045.board_service.validator;

import lombok.RequiredArgsConstructor;
import pre045.board_service.member.repository.MemberRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UsernameValidator implements ConstraintValidator<UsernameNotDuplicate, String> {
    private final MemberRepository memberRepository;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !memberRepository.existsByUsername(value);
    }
}