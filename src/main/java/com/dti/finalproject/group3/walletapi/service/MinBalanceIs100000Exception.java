package com.dti.finalproject.group3.walletapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Minimum balance is 100.000")
public class MinBalanceIs100000Exception extends RuntimeException {

}
