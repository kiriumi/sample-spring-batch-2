package sample.spring.batch.db;

import java.sql.SQLException;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class RollbackableDbWriter extends MyBatisBatchItemWriter<T> {

    private boolean isTest;

    @Value("#{jobParameters['testMode']}")
    public String testMode;

    @Autowired
    public PlatformTransactionManager transactionManager;

    @BeforeStep
    public void setTest() throws SQLException {
        this.isTest = testMode != null && testMode.equals("1") ? true : false;
    }

    @Override
    public void write(final List<? extends T> items) {

        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW); // 参考：https://www.techscore.com/tech/Java/Others/Spring/6/
        TransactionStatus status = transactionManager.getTransaction(definition);

        super.write(items);

        if (isTest) {
            transactionManager.rollback(status);
        } else {
            transactionManager.commit(status);
        }
    }

}
