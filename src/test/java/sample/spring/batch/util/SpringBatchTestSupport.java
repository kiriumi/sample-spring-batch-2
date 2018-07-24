package sample.spring.batch.util;

import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SpringBatchTestSupport {

	@Autowired
	@Qualifier("jobLauncherTestUtils")
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	@Qualifier("jobRepositoryTestUtils")
	private JobRepositoryTestUtils jobRepositoryTestUtils;

	protected static final String TEST_RESOURCES_PATH = "./src/test/resources/";
	protected static final String TEST_FILE_PATH = TEST_RESOURCES_PATH + "file/";
	protected static final String DBUNIT_PATH = TEST_RESOURCES_PATH + "db/dbunit/";

	private static IDatabaseTester databaseTester;
	private static IDatabaseConnection connection;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		setupConnection();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {

		jobRepositoryTestUtils.removeJobExecutions();
	}

	@After
	public void tearDown() throws Exception {

	}

	/**
	 * JobLauncherTestUtils を取得する
	 * @return JobLauncherTestUtils
	 */
	protected JobLauncherTestUtils getJobLauncherTestUtils() {
		return jobLauncherTestUtils;

	}

	/**
	 * JobRepositoryTestUtils を取得する
	 * @return JobLauncherTestUtils
	 */
	protected JobRepositoryTestUtils getJobRepositoryTestUtils() {
		return jobRepositoryTestUtils;

	}

	/**
	 * DB接続を取得する
	 * @return
	 */
	protected IDatabaseConnection getConnection() {
		return connection;

	}

	/**
	 * DB接続を行う（DBUnit用）
	 * @throws ClassNotFoundException
	 * @throws Exception
	 */
	private static void setupConnection() throws ClassNotFoundException, Exception {

		databaseTester = new JdbcDatabaseTester(
				"org.postgresql.Driver",
				"jdbc:postgresql://localhost:5432/spring_batch_db",
				"springrole",
				"springrole",
				"public");

		connection = databaseTester.getConnection();
	}

}
