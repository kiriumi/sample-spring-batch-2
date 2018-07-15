package sample.spring.batch.db.generator.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import sample.spring.batch.db.generator.dto.Db2;
import sample.spring.batch.db.generator.dto.Db2Example;

public interface Db2Mapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.db2
	 * @mbg.generated  Sun Jul 15 11:06:15 JST 2018
	 */
	long countByExample(Db2Example example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.db2
	 * @mbg.generated  Sun Jul 15 11:06:15 JST 2018
	 */
	int deleteByExample(Db2Example example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.db2
	 * @mbg.generated  Sun Jul 15 11:06:15 JST 2018
	 */
	int insert(Db2 record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.db2
	 * @mbg.generated  Sun Jul 15 11:06:15 JST 2018
	 */
	int insertSelective(Db2 record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.db2
	 * @mbg.generated  Sun Jul 15 11:06:15 JST 2018
	 */
	List<Db2> selectByExample(Db2Example example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.db2
	 * @mbg.generated  Sun Jul 15 11:06:15 JST 2018
	 */
	int updateByExampleSelective(@Param("record") Db2 record, @Param("example") Db2Example example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table public.db2
	 * @mbg.generated  Sun Jul 15 11:06:15 JST 2018
	 */
	int updateByExample(@Param("record") Db2 record, @Param("example") Db2Example example);
}