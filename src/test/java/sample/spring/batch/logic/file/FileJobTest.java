package sample.spring.batch.logic.file;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sample.spring.batch.util.SpringBatchTestSupport;

@ContextConfiguration(locations = { "/module-context_file.xml", "/test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class FileJobTest extends SpringBatchTestSupport {

	private static final String INPUT_FILE = TEST_FILE_PATH + "input.csv";
	private static final String OUTPUT_FILE = TEST_FILE_PATH + "output.csv";
	private static final String EXPECTED_FILE = TEST_FILE_PATH + "output_expected.csv";

	@Autowired
	@Qualifier("job1")
	private Job job;

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
		BatchStatus status = jobLauncehr.launchJob(params).getStatus();

		// 結果確認
		assertEquals(BatchStatus.COMPLETED, status);

		AssertFile.assertFileEquals(new FileSystemResource(EXPECTED_FILE),
				new FileSystemResource(OUTPUT_FILE));
	}

}
