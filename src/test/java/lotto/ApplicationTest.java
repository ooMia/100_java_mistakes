package lotto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Random;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

public class ApplicationTest {
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

    @DisplayName("Application 실행 테스트")
    @Test
    void integrationTest() {
        // given
        String[] args = {"8000", "1,2,3,4,5,6", "7"};
        Random randomNumberMock = Mockito.mock(Random.class);
        int[][] randomValues = new int[][]{
                {1, 2, 3, 41, 42, 43},
                {1, 2, 9, 41, 42, 43},
                {3, 5, 11, 16, 32, 38},
                {7, 11, 16, 35, 36, 44},
                {13, 14, 16, 38, 42, 44},
                {2, 13, 22, 32, 38, 41},
                {2, 13, 22, 32, 38, 43},
                {2, 13, 22, 32, 38, 42}
        };
        String expected = makeStringByArgs(randomValues);
        OngoingStubbing<Integer> stubbing = when(randomNumberMock.nextInt(45));
        for (int[] values : randomValues) {
            for (int value : values) {
                stubbing = stubbing.thenReturn(value);
            }
        }
        // when
        Game game = new Game(args, randomNumberMock);
        game.runGame();
        // then
        assertEquals(
                expected.trim().replaceAll("\r", ""),
                actual.toString().trim().replaceAll("\r", ""));
    }

    private String makeStringByArgs(int[][] randomValues) {
        String inputMoney = "구입금액을 입력해 주세요." + lf; //+ args[0] + lf;
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d개를 구매했습니다.\n", 8));
        for (int[] randomValue : randomValues) {
            sb.append(Arrays.toString(Arrays.stream(randomValue).map(n -> n + 1).toArray()));
            sb.append("\n");
        }
        String outputPurchasedLotto = sb.toString();
        String inputWinningLotto = "당첨 번호를 입력해 주세요." + lf; // + args[1] + lf;
        String inputWinningBonus = "보너스 번호를 입력해 주세요." + lf; // + args[2] + lf;
        String outputPrizeStatistics = """
                당첨 통계
                ---
                3개 일치 (5,000원) - 1개
                4개 일치 (50,000원) - 0개
                5개 일치 (1,500,000원) - 0개
                5개 일치, 보너스 볼 일치 (30,000,000원) - 0개
                6개 일치 (2,000,000,000원) - 0개
                총 수익률은 62.5%입니다.
                """.replace("\n", lf).trim();
        return concatLines(inputMoney, outputPurchasedLotto, inputWinningLotto, inputWinningBonus,
                outputPrizeStatistics);
    }

    private String concatLines(String... lines) {
        return String.join(lf, lines);
    }
}
