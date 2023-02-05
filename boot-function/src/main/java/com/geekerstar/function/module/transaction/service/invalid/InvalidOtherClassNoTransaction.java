package com.geekerstar.function.module.transaction.service.invalid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author geekerstar
 * @date 2023/2/5 10:51
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class InvalidOtherClassNoTransaction {

    private final InvalidOtherClassWithTransaction invalidOtherClassWithTransaction;

    public void methodNoTransaction(){
        invalidOtherClassWithTransaction.methodTransaction();
    }
}
