package sample.spring.batch.util;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class SpringBatchTestSupport {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

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
		clearJobRepository();
	}

	@After
	public void tearDown() throws Exception {
		clearJobRepository();
	}

	/**
	 * JobLauncherTestUtils を返却する
	 * @return JobLauncherTestUtils
	 */
	protected JobLauncherTestUtils getJobLauncherTestUtils() {
		return jobLauncherTestUtils;

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

	/**
	 * ジョブリポジトリをクリアする
	 * @throws MalformedURLException
	 * @throws DataSetException
	 * @throws DatabaseUnitException
	 * @throws SQLException
	 */
	private void clearJobRepository()
			throws MalformedURLException, DataSetException, DatabaseUnitException, SQLException {
		// ジョブリポジトリをクリアする
		IDataSet clearJobRepository = new FlatXmlDataSetBuilder()
				.build(new File(DBUNIT_PATH + "clear-job-repository.xml"));
		DatabaseOperation.DELETE_ALL.execute(connection, clearJobRepository);
	}

	@Test
	public void testHoge() {
		System.out.println("hoge");
	}

}
