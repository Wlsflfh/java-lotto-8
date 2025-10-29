package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class LottoNumberTest {

    @Test
    @DisplayName("로또 번호가 올바른 범위인지 확인한다")
    void validNumberRangeTest() {
        // given
        int validUserInput = 5;

        // when - then
        assertDoesNotThrow(() -> {new LottoNumber(validUserInput);});
    }

    @Test
    @DisplayName("범위를 넘어간 로또 번호에 대하여 예외를 발생시키는지 확인한다")
    void inValidNumberRangeTest() {
        // given
        int inValidUserInput = 55;

        // when - then
        assertThatThrownBy(() -> new LottoNumber(inValidUserInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 1에서 45 사이의 숫자여야 합니다.");
    }
}
