package com.lx.dao.impl;

import com.lx.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDao {

    //使用DbUtils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    public int update(String sql,Object ...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     *查询返回一个JavaBean的sql语句
     */
    public <T> T queryForOne(Class<T> type,String sql,Object ...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql , new BeanHandler<T>(type), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     *查询返回多个JavaBean的sql语句
     * @return
     */
    public <T> List<T> queryForList(Class<T> type,String sql,Object ...args){
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection,sql , new BeanListHandler<T>(type), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }

    }

    /**
     * 执行返回一行一列(聚合函数)的sql语句
     */
    public Object queryForSingleValue(String sql,Object ...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }

    }
}
