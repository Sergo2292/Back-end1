package org.example.HW6;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.HW6.db.dao.CategoriesMapper;
import org.example.HW6.db.dao.ProductsMapper;
import org.example.HW6.db.model.CategoriesExample;
import org.example.HW6.db.model.ProductsExample;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractTest {
    private static SqlSession sqlSession;
    private static CategoriesMapper categoriesMapper;
    private static ProductsMapper productsMapper;
    private static CategoriesExample categoriesExample;
    private static ProductsExample productsExample;

    public static SqlSession getSqlSession() {
        return sqlSession;
    }

    public static CategoriesMapper getCategoriesMapper() {
        return categoriesMapper;
    }

    public static ProductsMapper getProductsMapper() {
        return productsMapper;
    }

    public static CategoriesExample getCategoriesExample() {
        return categoriesExample;
    }

    public static ProductsExample getProductsExample() {
        return productsExample;
    }

    @BeforeAll
    static void setUp() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSession = sqlSessionFactory.openSession();
    }

    @BeforeEach
    void createMappers() {
        categoriesMapper = sqlSession.getMapper(CategoriesMapper.class);
        categoriesExample = new CategoriesExample();
        productsMapper = sqlSession.getMapper(ProductsMapper.class);
        productsExample = new ProductsExample();
    }

    @AfterAll
    static void tearDown() {
        sqlSession.close();
    }
}
