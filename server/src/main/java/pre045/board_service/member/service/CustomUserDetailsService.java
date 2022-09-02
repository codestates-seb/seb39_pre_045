package pre045.board_service.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pre045.board_service.exception.BusinessLogicException;
import pre045.board_service.member.repository.MemberRepository;
import pre045.board_service.member.token.entity.Member;

import java.util.Collections;

import static pre045.board_service.exception.ExceptionCode.MEMBER_NOT_EXIST;


@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return memberRepository.findByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new BusinessLogicException(MEMBER_NOT_EXIST));
    }

    private UserDetails createUserDetails(Member member) {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(member.getAuthority().toString());

        log.info("grantedAuthority={}", grantedAuthority);

        return new User(
                String.valueOf(member.getMemberId()),
                member.getPassword(),
                Collections.singleton(grantedAuthority)
        );
    }
}
