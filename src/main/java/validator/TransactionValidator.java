package validator;

import org.apache.ibatis.transaction.Transaction;
import org.springframework.validation.Errors;

public class TransactionValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Transaction.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
