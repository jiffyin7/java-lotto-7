package lotto.model.lotto;

import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

  public static Lotto from(List<Integer> numbers) {
      return new Lotto(sortNumbers(numbers));
  }

  private static List<Integer> sortNumbers(List<Integer> numbers) {
      return numbers.stream()
          .sorted()
          .collect(Collectors.toList());
  }

  private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    // TODO: 추가 기능 구현
}