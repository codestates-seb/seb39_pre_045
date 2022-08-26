package pre045.board_service.member;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@Validated
@Slf4j
public class MemberController {


    @PostMapping("/signup")
    public ResponseEntity signUpMember(){

        // Todo

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity signInMember(){

        // Todo

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity logoutMember(){

        // Todo

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{member-id}/edit")
    public ResponseEntity updateMember(){

        // Todo

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/recovery")
    public ResponseEntity recoveryMember(){

        // Todo

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{member-id}")
    public ResponseEntity deleteMember(){

        // Todo

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{username}")
    public ResponseEntity getMyPage(){

        // Todo

        return new ResponseEntity<>(HttpStatus.OK);
    }




}
