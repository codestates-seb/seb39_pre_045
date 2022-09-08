package pre045.board_service.member.token.refreshtoken.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //Entity가 BaseTimeEntity 상속 시 여기 있는 필드도 컬럼으로 인식하게 됨
@EntityListeners(AuditingEntityListener.class) //Auditing 기능 포함함
public class BaseTimeEntity {

    @CreatedDate  //생성일 자동 생성
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate  //수정일 자동 갱신
    private LocalDateTime modifiedAt;

}
