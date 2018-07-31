package sample.spring.batch.db.generator.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import sample.spring.batch.db.generator.dto.Db1;
import sample.spring.batch.db.generator.dto.Db1Example;

public interface Db1Mapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table public.db1
     *
     * @mbg.generated Sun Jul 15 11:06:15 JST 2018
     */
    long countByExample(Db1Example example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table public.db1
     *
     * @mbg.generated Sun Jul 15 11:06:15 JST 2018
     */
    int deleteByExample(Db1Example example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table public.db1
     *
     * @mbg.generated Sun Jul 15 11:06:15 JST 2018
     */
    int insert(Db1 record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table public.db1
     *
     * @mbg.generated Sun Jul 15 11:06:15 JST 2018
     */
    int insertSelective(Db1 record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table public.db1
     *
     * @mbg.generated Sun Jul 15 11:06:15 JST 2018
     */
    List<Db1> selectByExample(Db1Example example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table public.db1
     *
     * @mbg.generated Sun Jul 15 11:06:15 JST 2018
     */
    int updateByExampleSelective(@Param("record") Db1 record, @Param("example") Db1Example example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds
     * to the database table public.db1
     *
     * @mbg.generated Sun Jul 15 11:06:15 JST 2018
     */
    int updateByExample(@Param("record") Db1 record, @Param("example") Db1Example example);
}
