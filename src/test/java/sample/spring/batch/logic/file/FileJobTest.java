package sample.spring.batch.logic.file;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.test.AssertFile;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/module-context_file.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class FileJobTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	private static final String ROOT_PATH = System.getProperty("user.dir");

	private static final String OUTPUT_FILE = ROOT_PATH + "/src/test/resources/file/output.csv";
	private static final String EXPECTED_FILE = ROOT_PATH + "/src/test/resources/file/output_expected.csv";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws Exception {

		JobParametersBuilder paramsBuilder = new JobParametersBuilder();

		paramsBuilder.addString("inputFile", "./src/test/resources/file/input.csv");
		paramsBuilder.addString("outputFile", "./src/test/resources/file/output.csv");

		JobParameters params = paramsBuilder.toJobParameters();

		BatchStatus status = jobLauncherTestUtils.launchJob(params).getStatus();

		assertEquals(BatchStatus.COMPLETED, status);

		AssertFile.assertFileEquals(new FileSystemResource(EXPECTED_FILE),
				new FileSystemResource(OUTPUT_FILE));
	}

}