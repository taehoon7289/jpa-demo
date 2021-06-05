package com.dev.blackmango;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.modifyUris;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

public interface ApiDocumentUtils {

  public static OperationRequestPreprocessor getDocumentRequest() {
    return preprocessRequest(
        // (1) 문서상 uri 를 기본값인 http://localhost:8080 에서 https://docs.api.com 으로 변경하기 위해 사용합니다.
        modifyUris()
            .scheme("https")
            .host("docs.api.com")
            .removePort(),
        // (2) 문서의 request 을 예쁘게 출력하기 위해 사용합니다.
        prettyPrint());
  }

  public static OperationResponsePreprocessor getDocumentResponse() {
    // (3) 문서의 response 를 예쁘게 출력하기 위해 사용합니다.
    return preprocessResponse(prettyPrint());
  }
}
