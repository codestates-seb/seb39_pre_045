package pre045.board_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pre045.board_service.comment.CommentRepository;
import pre045.board_service.member.Member;
import pre045.board_service.member.MemberRepository;
import pre045.board_service.question.QuestionRepository;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

//	Stub Data Init을 위한 코드
	@Bean
	public DataInit init(MemberRepository memberRepository, QuestionRepository questionRepository) {
		return new DataInit(memberRepository, questionRepository);
	}

}
