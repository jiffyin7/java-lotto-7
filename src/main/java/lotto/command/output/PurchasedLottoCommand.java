package lotto.command.output;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.dto.MatchResult;
import lotto.dto.MatchResults;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.PurchasedLottos;
import lotto.service.lotto.constant.MatchBonusEnum;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 11. 2.
 */
public class PurchasedLottoCommand implements OutputCommand {
  private final PurchasedLottos purchasedLottos;

  private PurchasedLottoCommand(PurchasedLottos purchasedLottos) {
    this.purchasedLottos = purchasedLottos;
  }

  public static PurchasedLottoCommand from (PurchasedLottos purchasedLottos) {
    return new PurchasedLottoCommand(purchasedLottos);
  }

  @Override
  public Object execute(Object parameter) {
    return null;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    List<Lotto> lottos = purchasedLottos.getLottos();
    return String.format("%d개를 구매헀습니다.%s%s",
        lottos.size(),
        System.lineSeparator(),
        printLottosNumbers(lottos));
  }

  private String printLottosNumbers(List<Lotto> lottos) {
    return lottos.stream()
        .map(this::printLottoNumbers)
        .collect(Collectors.joining(System.lineSeparator()));
  }

  private String printLottoNumbers(Lotto lotto) {
    return "[" + lotto.getNumbers().stream()
        .map(Object::toString)
        .collect(Collectors.joining(",")) + "]";
  }
}