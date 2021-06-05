package com.dev.blackmango;

import static com.dev.blackmango.ApiDocumentUtils.getDocumentRequest;
import static com.dev.blackmango.ApiDocumentUtils.getDocumentResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dev.blackmango.common.component.JwtTokenProvider;
import com.dev.blackmango.model.dto.board.BoardDTO;
import com.dev.blackmango.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class BlackmangoApplicationTests {

  private MockMvc mockMvc;

  @Autowired
  private BoardService boardService;
  @Autowired
  private JwtTokenProvider jwtTokenProvider;
  @Autowired
  private ObjectMapper objectMapper;

  @BeforeEach
  public void setUp(WebApplicationContext webApplicationContext,
      RestDocumentationContextProvider restDocumentation) {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(documentationConfiguration(restDocumentation)).build();
  }

  @Test
  void contextLoads() throws Exception {
    System.out.println("테스트!!!");
//    this.mockMvc.perform(get("/api/sample").accept(MediaType.APPLICATION_JSON))
//        .andExpect(status().isOk()).andDo(document("index"));

    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhY2Nlc3NfdG9rZW4iLCJkYXRhIjp7InVzZXJObyI6MSwiaWQiOiJ1c2VySWQiLCJuYW1lIjoidXNlck5hbWUifSwiaWF0IjoxNjIyODg3ODc2LCJleHAiOjE2MjI4ODk2NzZ9.rLayI8orMC5lNPD_8_XjoFpjMuWSVEHEMQrXc2KBQS4";
//    Map tokenMap = jwtTokenProvider.getData(token);
    BoardDTO boardDTO = BoardDTO.builder()
        .boardNo(0)
        .title("테스트제목")
        .contents("테스트내용")
        .build();
//    given(boardService.saveBoard(Long.parseLong(tokenMap.getOrDefault("userNo", -1).toString()),
//        boardDTO))
//        .willReturn(15L); // (3)

    ResultActions result = this.mockMvc.perform(
        post("/api/v1/boards")
            .header("Authorization", token)
            .content(objectMapper.writeValueAsString(boardDTO))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
    );

    result.andExpect(status().isOk())
        .andDo(document("board-insert", // (4)
//            getDocumentRequest(),
//            getDocumentResponse(),
//            pathParameters(
//                parameterWithName("boardDTO").description("게시글 제목/내용")
//            ),
            requestFields(
                fieldWithPath("boardNo").type(JsonFieldType.NUMBER).description("수정 no"),
                fieldWithPath("title").type(JsonFieldType.STRING).description("제목"),
                fieldWithPath("contents").type(JsonFieldType.STRING).description("내용")
            ),
            responseFields(
                fieldWithPath("code").type(JsonFieldType.NUMBER).description("결과코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지"),
                fieldWithPath("responseData").type(JsonFieldType.NUMBER).description("게시글 NO")
            )
        ));
  }

}
