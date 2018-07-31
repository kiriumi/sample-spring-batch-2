package sample.spring.batch.example;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.spring.batch.util.SpringBatchTestSupport;

@ContextConfiguration(locations = { "/module-context_example.xml", "/test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleJobTest extends SpringBatchTestSupport {

    @Autowired
    @Qualifier("job1")
    private Job job;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Override
    @Before
    public void setUp() throws Exception {
    }

    @Override
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testConnection() throws Exception {

        getJobLauncherTestUtils().setJob(job);
        JobExecution jobExecution = getJobLauncherTestUtils().launchJob();

        BatchStatus status = jobExecution.getStatus();
        assertEquals(BatchStatus.COMPLETED, status);
    }

}
