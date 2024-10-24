package sample.cafekiosk.spring.api.service.mail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.spring.client.mail.MailSendClient;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistory;
import sample.cafekiosk.spring.domain.history.mail.MailSendHistoryRepository;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock // 객체 mocking - 객체 stubbing
//    @Spy // 객체의 특정 메소드만 stubbing (잘 안쓰임)
    private MailSendClient mailSendClient;

    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;

    @InjectMocks
    private MailService mailService;

    // @MockBean 없이 (sprint container 없이)
    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        // given
//         @Mock // 이용시
//        Mockito.when(mailSendClient.sendMail(anyString(), anyString(), anyString(), anyString()))
//            .thenReturn(true);

        // 사용 권장 BDD 관점이랑 메소드 명이 일치 (given, when, then)
        BDDMockito.given(mailSendClient.sendMail(anyString(), anyString(), anyString(), anyString()))
            .willReturn(true);
//        @Spy 이용시
//        doReturn(true)
//            .when(mailSendClient)
//            .sendMail(anyString(), anyString(), anyString(), anyString());


        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        assertThat(result).isTrue();
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
    }
}