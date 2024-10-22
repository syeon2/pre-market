package io.syeony.premarket.account.domain.processor.repository;

import io.syeony.premarket.account.domain.model.Account;
import io.syeony.premarket.account.domain.model.vo.MemberId;

public interface AccountRepository {

	MemberId save(Account account);
}
