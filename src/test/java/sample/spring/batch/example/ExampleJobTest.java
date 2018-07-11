package sample.spring.batch.example;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "/module-context_example.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ExampleJobTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

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
	public void testConnection() throws Exception {

		BatchStatus status = jobLauncherTestUtils.launchJob().getStatus();
		assertEquals(BatchStatus.COMPLETED, status);
	}

}
