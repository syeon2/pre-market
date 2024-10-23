package io.syeony.premarket.account.domain.processor.writer;

import io.syeony.premarket.account.domain.model.Account;
import io.syeony.premarket.account.domain.model.vo.MemberId;

public interface AccountWriter {

	MemberId save(Account account);
}
