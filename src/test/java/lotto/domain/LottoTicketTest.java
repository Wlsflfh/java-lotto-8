package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LottoTicketTest {

    @Test
    @DisplayName("matchCount 를 올바르게 세는지 확인한다")
    void countMatchingTest() {
        // given
        Lotto lotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

        // when
        LottoTicket lottoTicket = new LottoTicket(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(7), new LottoNumber(8)));

        // then
        assertEquals(lottoTicket.countMatching(lotto), 4);
    }

    @Test
    @DisplayName("보너스 번호가 로또에 존재하는지 확인한다")
    void containsTest() {
        // given
        LottoNumber lottoNumber = new LottoNumber(1);

        // when
        LottoTicket lottoTicket = new LottoTicket(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(7), new LottoNumber(8)));

        // then
        assertTrue(lottoTicket.contains(lottoNumber));
    }

    @Test
    @DisplayName("보너스 번호가 로또에 존재하지 않는지 확인한다")
    void notContainsTest() {
        // given
        LottoNumber lottoNumber = new LottoNumber(10);

        // when
        LottoTicket lottoTicket = new LottoTicket(List.of(new LottoNumber(1), new LottoNumber(2), new LottoNumber(3), new LottoNumber(4), new LottoNumber(7), new LottoNumber(8)));

        // then
        assertFalse(lottoTicket.contains(lottoNumber));
    }
}
