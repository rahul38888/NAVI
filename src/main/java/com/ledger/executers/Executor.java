package com.ledger.executers;

import com.ledger.pojo.Result;
import com.ledger.pojo.RequestParameters;

public interface Executor<T> {
	Result execute(RequestParameters parameters);
}
