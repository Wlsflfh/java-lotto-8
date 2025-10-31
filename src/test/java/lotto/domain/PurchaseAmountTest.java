package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MoneyTest {

    @Test
    @DisplayName("구입 금액에 해당하는 만큼 로또를 발행하는지 확인한다.")
    void calculateTicketCountTest() {
        // given
        int userInput = 8000;
        Money money = new Money(userInput);

        // when
        int ticketCount = money.calculateTicketCount();

        // then
        assertThat(ticketCount).isEqualTo(8);
    }

    @Test
    @DisplayName("음수 혹은 0을 입력했을 때 예외를 발생시키는지 확인한다")
    void validateMinRangeTest() {
        // given
        int userInput1 = -2000;
        int userInput2 = 0;

        // when - then
        assertThatThrownBy(() -> new Money(userInput1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 양수만 가능합니다.");

        // when - then
        assertThatThrownBy(() -> new Money(userInput2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 양수만 가능합니다.");
    }

    @Test
    @DisplayName("최대 금액인 백만원을 넘어가는 입력에 대해 예외를 발생시키는지 확인한다")
    void validateMaxRangeTest() {
        // given
        int userInput = 10000000;

        // when - then
        assertThatThrownBy(() -> new Money(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1,000,000원 이하만 가능합니다.");
    }

    @Test
    @DisplayName("천원 단위의 숫자를 입력하지 않았을 때 예외를 발생시키는지 확인한다")
    void validateUnitTest() {
        // given
        int userInput = 999;

        // when - then
        assertThatThrownBy(() -> new Money(userInput))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
    }
}
