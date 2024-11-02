package lotto.service.lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import lotto.command.validate.BonusCommand;
import lotto.command.validate.LottoCommand;
import lotto.command.validate.ValidateCommand;
import lotto.dto.BonusUserInput;
import lotto.dto.MatchResult;
import lotto.dto.MatchResults;
import lotto.dto.WinningLottoUserInput;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.PurchasedLottos;
import lotto.model.lotto.WinningLotto;
import lotto.service.lotto.constant.LottoConstant;

/**
 * @author : jiffyin7@gmail.com
 * @since : 24. 10. 31.
 */
public class LottoService {
  private LottoCommand lottoCommand;
  private BonusCommand bonusCommand;

  public LottoService (LottoCommand lottoCommand,
      BonusCommand bonusCommand
      ) {
    this.lottoCommand = lottoCommand;
    this.bonusCommand = bonusCommand;
  }

  public int getPrice() {
    return LottoConstant.PRICE;
  }

  public PurchasedLottos publishPurchaseLottos (int lottoCount) {
    return PurchasedLottos.from(createLottos(lottoCount));
  }

  public LottoCommand getLottoCommand() {
    return this.lottoCommand;
  }

  private List<Lotto> createLottos(int count) {
    List<Lotto> lottos = new ArrayList<>();
    for (int i = 0; i < count; ++i) {
      lottos.add(createLotto(generateRandomNumbers()));
    }
    return lottos;
  }

  private List<Integer> generateRandomNumbers() {
    return Randoms.pickUniqueNumbersInRange(1, 45, 6);
  }

  public Lotto createLotto(List<Integer> numbers) {
    return Lotto.from(numbers);
  }

  public WinningLotto createWinningLotto (WinningLottoUserInput userInput) {
    return WinningLotto.from(userInput);
  }

  public WinningLotto addBonusNumber (WinningLotto winningLotto, BonusUserInput userInput) {
    return winningLotto.addBonus(userInput);
  }

  public BonusCommand getBonusCommand() {
    return this.bonusCommand;
  }

  public MatchResults matchWinningLotto(WinningLotto winningLotto, PurchasedLottos purchasedLottos) {
    MatchResults matchResults = MatchResults.createMatchResults();
    for (Lotto purchasedLotto : purchasedLottos.getLottos()) {
      MatchResult matchResult = winningLotto.match(purchasedLotto);
      matchResults.add(matchResult);
    }
    return matchResults;
  }
}