package lotto.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import lotto.view.sin.BonusInputView;
import lotto.view.sin.MoneyInputView;
import lotto.view.sin.WinningLottoInputView;
import lotto.view.sout.LottoPurchaseOutputView;
import lotto.view.sout.PrizeStatisticsOutputView;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HeaderTest {

    private final String lf = System.lineSeparator();
    private ByteArrayOutputStream actual;

    @BeforeEach
    void setUp() {
        actual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actual));
    }

    @AfterEach
    void tearDown() {
        System.setOut(System.out);
    }

    @DisplayName("구입금액 입력 헤더 출력 테스트")
    @Test
    void moneyInputHeader() {
        // given
        String expected = lf + "구입금액을 입력해 주세요." + System.lineSeparator();
        // when
        new MoneyInputView().header();
        // then
        Assertions.assertEquals(expected, actual.toString());
    }

    @DisplayName("당첨 번호 입력 헤더 출력 테스트")
    @Test
    void winningLottoInputHeader() {
        // given
        String expected = "당첨 번호를 입력해 주세요." + lf;
        // when
        new WinningLottoInputView().header();
        // then
        Assertions.assertEquals(expected, actual.toString());
    }

    @DisplayName("보너스 번호 입력 헤더 출력 테스트")
    @Test
    void winningBonusNumberInputHeader() {
        // given
        String expected = lf + "보너스 번호를 입력해 주세요." + lf;
        // when
        new BonusInputView().header();
        // then
        Assertions.assertEquals(expected, actual.toString());
    }

    @DisplayName("로또 구매 헤더 출력 테스트")
    @Test
    void lottoPurchaseOutputHeader() {
        // given
        String expected = "";
        // when
        new LottoPurchaseOutputView();
        // then
        Assertions.assertEquals(expected, actual.toString());
    }

    @DisplayName("당첨 통계 헤더 출력 테스트")
    @Test
    void prizeStatisticsOutputHeader() {
        // given
        String expected = lf + "당첨 통계" + lf + "---" + lf;
        // when
        new PrizeStatisticsOutputView();
        // then
        Assertions.assertEquals(expected, actual.toString());
    }
}
