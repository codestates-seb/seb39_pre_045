package pre045.board_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pre045.board_service.member.repository.MemberRepository;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	@Bean
	public DataInit init(MemberRepository memberRepository) {
		return new DataInit(memberRepository);
	}

}
