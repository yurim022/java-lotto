package lotto.domain;

import lombok.Getter;
import lotto.domain.type.RankType;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

public class LottoCalculator {
    @Getter
    private MatchResult matchResult;

    public LottoCalculator(WinningLotto winningLotto, List<Lotto> lottos) {
        this.matchResult = new MatchResult();

        for (Lotto lotto : lottos) {
            RankType rankType = RankType.getRank(winningLotto.getMatchCount(lotto), winningLotto.matchBonus(lotto));

            matchResult.addResult(rankType);
        }
    }

    public BigDecimal getWinningPercentage(int investment) {
        int totalReward = matchResult.getTotalReward();

        return new BigDecimal(totalReward).divide(new BigDecimal(investment), new MathContext(2, RoundingMode.DOWN));
    }

    public int getCount(RankType rankType) {
        return matchResult.getCount(rankType);
    }
}