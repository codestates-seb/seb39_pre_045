package pre045.board_service.question.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import pre045.board_service.util.RestDocsTestSupport;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(QuestionController.class)
@MockBean(JpaMetamodelMappingContext.class)
class QuestionControllerTest extends RestDocsTestSupport {

}