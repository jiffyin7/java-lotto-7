package lotto.command.validate;

import static lotto.common.constant.Lotto.LOTTO_MAXIMUM_NUMBER;
import static lotto.common.constant.Lotto.LOTTO_MINIMUM_NUMBER;

import lotto.dto.BonusUserInput;
import lotto.dto.UserInput;


/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 1.
 */
public class BonusCommand implements ValidateCommand {
  private static final String ASK = "보너스 번호를 입력해 주세요.";

  @Override
  public UserInput execute(String input) {
    try {
      return validate(input);
    } catch (IllegalArgumentException | IllegalStateException e) {
      view.displayOutput(e.getMessage());
      return redo();
    }
  }

  private BonusUserInput validate(String input) {
    int number = validateIntegerRange(input, LOTTO_MINIMUM_NUMBER, LOTTO_MAXIMUM_NUMBER);
    return BonusUserInput.from(number);
  }

  @Override
  public String ask() {
    return ASK;
  }
}