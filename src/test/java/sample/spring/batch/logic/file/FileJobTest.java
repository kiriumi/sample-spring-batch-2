package sample.spring.batch.logic.file;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ContextConfiguration;

import sample.spring.batch.util.SpringBatchTestSupport;

@ContextConfiguration(locations = { "/module-context_file.xml", "/test-context.xml" })
public class FileJobTest extends SpringBatchTestSupport {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	private static final String INPUT_FILE = TEST_FILE_PATH + "input.csv";
	private static final String OUTPUT_FILE = TEST_FILE_PATH + "output.csv";
	private static final String EXPECTED_FILE = TEST_FILE_PATH + "output_expected.csv";

	@Test
	public void test() throws Exception {

		JobParametersBuilder paramsBuilder = new JobParametersBuilder();

		paramsBuilder.addString("inputFile", INPUT_FILE);
		paramsBuilder.addString("outputFile", OUTPUT_FILE);

		JobParameters params = paramsBuilder.toJobParameters();

		BatchStatus status = jobLauncherTestUtils.launchJob(params).getStatus();

		assertEquals(BatchStatus.COMPLETED, status);

		AssertFile.assertFileEquals(new FileSystemResource(EXPECTED_FILE),
				new FileSystemResource(OUTPUT_FILE));
	}

}
