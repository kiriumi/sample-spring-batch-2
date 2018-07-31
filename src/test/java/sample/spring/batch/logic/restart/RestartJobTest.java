package sample.spring.batch.logic.restart;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.spring.batch.util.SpringBatchTestSupport;

@ContextConfiguration(locations = { "/module-context_restart.xml", "/test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class RestartJobTest extends SpringBatchTestSupport {

	private static final String INPUT_FILE = TEST_FILE_PATH + "input.csv";
	private static final String OUTPUT_FILE = TEST_FILE_PATH + "output.csv";

	@Autowired
	@Qualifier("job1")
	private Job job;

	@Autowired
	private JobLauncher restartJobLauncher;

	@Test
	public void test() throws Exception {

		// ジョブパラメータの設定
		JobParametersBuilder paramsBuilder = new JobParametersBuilder();
		paramsBuilder.addString("inputFile", INPUT_FILE);
		paramsBuilder.addString("outputFile", OUTPUT_FILE);
		JobParameters params = paramsBuilder.toJobParameters();

		// ジョブの開始
		JobLauncherTestUtils jobLauncehr = getJobLauncherTestUtils();
		jobLauncehr.setJob(job);
		JobExecution jobExecution = jobLauncehr.launchJob(params);
		BatchStatus status = jobExecution.getStatus();

		// 結果確認
		assertEquals(BatchStatus.FAILED, status);

		// ジョブの再実行
		JobExecution restartedJobExecution = restartJobLauncher.run(job, params);
		BatchStatus restartedStatus = restartedJobExecution.getStatus();

		// 結果確認
		assertEquals(BatchStatus.COMPLETED, restartedStatus);
	}

}
