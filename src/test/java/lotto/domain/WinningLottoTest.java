package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class WinningLottoTest {

    @Test
    @DisplayName("matchCount 를 올바르게 세는지 확인한다")
    void countMatchingNumbersTest() {
        // given
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(8));

        // when
        LottoTicket lottoTicket = new LottoTicket(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(7), new LottoNumber(8)));

        // then
        assertEquals(winningLotto.countMatchingNumbers(lottoTicket), 4);
    }

    @Test
    @DisplayName("보너스 번호가 로또에 존재하는지 확인한다")
    void containsBonusNumberTest() {
        // given
        WinningLotto winningLotto = new WinningLotto(new Lotto(List.of(1, 2, 3, 4, 5, 6)), new LottoNumber(8));

        // when
        LottoTicket lottoTicket = new LottoTicket(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(7), new LottoNumber(8)));

        // then
        assertTrue(winningLotto.containsBonusNumber(lottoTicket));
    }

    @Test
    @DisplayName("보너스 번호가 당첨 번호와 중복될 때 예외를 던지는지 확인한다")
    void validateDuplicateTest() {
        // given
        Lotto winningNumbers = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonusNumber = new LottoNumber(6);

        // when - then
        assertThatThrownBy(() -> new WinningLotto(winningNumbers, bonusNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
    }
}
