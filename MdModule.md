# 说明

## 基础框架

基础分为两个module

* pims-base 定义了一些异常类，工具类以及logger类和基础分页bean类
* pims-core 定义了springboot的配置，redis配置，ehcache配置，事务配置以及基础的controller，serivce和DAO类

> pims-core 依赖 pims-base，在pims-core的pom.xml中已经声明

---

## 使用

遵循模块化规则，正常运行分以下几步：
1. 首先创建自己的module，然后在自己module的pom.xml文件里声明依赖pims-core模块，以下操作均在新建module中进行
2. 在resources文件夹下创建application.yml，并在其中声明激活的配置文件为core
    ``` yml
    spring:
      profiles:
        active: core
3. 指定数据库，在application.yml中声明数据库连接的url，driver，username，password 类似于
    ``` yml
    spring:
      datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://localhost:3306/ttt
        username: ttt
        password: root123
    ```
4. 添加flyway，在pom文件中添加
    ``` xml
    <dependency>
        <groupId>org.flywaydb</groupId>
        <artifactId>flyway-core</artifactId>
    </dependency>
    ```
    在yml文件中声明
    ``` yml
    flyway:
        baseline-on-migrate: true
        baseline-version: 0.0.0
        out-of-order: true
    ```
    在resources文件夹下建立
    ```
    db
        migration
            V0.0.1_1__XXXXXX.sql
    ```
4. 创建包层级，应为 
    ``` text
    com.ys.xxx
    ```
    并且在com.ys下创建springboot启动类
    ```
    com
        ys
            Xxx
            XxxApplication.java
    ```
5. 在XxxApplication.java中添加类注解
    > @SpringBootApplication(scanBasePackages = "com.ys")

---

## 一些规范

对于某个功能模块，该模块内应分为controller，model，service文件夹。controller文件夹存放controller，model存放model类和mapper文件，service下放接口以及实现，Xxx首字母大写
```
Xxx
    controller
        XxxController.java
    model
        Xxx.java
        XxxMapper.xml
    service
        impl
            XxxServiceImpl.java
        IXxxService.java
```
* 最好不要在 controller 层里写业务代码，尤其是操作数据库相关代码，通通放在service层
* controller 层统一返回值为 ResponseEntity，在这个实例中存放返回值以及响应码
* model 如果需要分页则需要继承 BasicBean，若需序列化则实现 Serializable 接口并声明 serialVersionUID
* mapper 命名需规范，应为model名 + Mapper，在mapper中定义基础的返回映射BaseResultMap
* 数据库表应为T_Xxx，数据库字段名全部为大写+下划线格式，对应model中驼峰式
* mapper 中需要实现的基础sql为 selectByPrimaryKey, findByConditionInfo, deleteByPrimaryKey, deleteByEntity, insertSelective, updateByPrimaryKeySelective
    ``` sql
    <select id="selectByPrimaryKey" resultMap="BaseResultMap" paramterType="java.lang.String">
        select Xxx from T_Xxx where ID = #{id,jdbcType=CHAR}
    </select>

    <select id="findByConditionInfo" resultMap="BaseResultMap" parameterType="com.ys.xxx.model.Xxx">
        select Xxx from T_Xxx
        <where>
            <if test="column1 != null and column1 != ''">
                and COLUMN_1 = #{column1,jdbcType=NVARCHAR} 
            </if>
            <if test="column2 != null">
                and COLUMN_2 = #{column2,jdbcType=DECIMAL}
            </if>
        </where>
    </select>

    <delete id="deleteByPrimaryKey" parameterType="java.lang.String">
        delete from T_Xxx where ID = #{id,jdbcType=CHAR}
    </delete>

    <delete id="deleteByEntity" parameterType="com.ys.xxx.model.Xxx">
        delete from T_Xxx
        where
        <if test="column1 != null and column1 != ''">
            COLUMN_1 = #{column1,jdbcType=NVARCHAR}
        </if>
    </delete>

    <insert id="insertSelective" parameterType="com.ys.xxx.model.Xxx">
        insert into T_Xxx
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="column1 != null and column1 != ''">
                COLUMN_1,
            </if>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides=",">
            <if test="column1 != null and column1 != ''">
                #{column1,jdbcType=NVARCHAR}
            </if>
        </trim>
    </insert>

    <update id="updateByPrimaryKeySelective" parameterType="com.ys.xxx.model.Xxx">
        update T_Xxx
        <set>
            <if test="column1 != null">
                COLUMN_1 = #{column1,jdbcType=NVARCHAR},
            </if>
        </set>
        where ID = #{id,jdbcType=CHAR}
    </update>
    ```
* mapper 中语句应主要为该表的语句，一些联查也应将该表作为主表联查
* service 层应写接口 IXxxService.java 放在service文件夹中，其接口实现 XxxServiceImpl.java 放在impl文件夹中
* IXxxService.java 方法若为CRUD则需要继承 IBaseService<T>，并指明泛类T
    ``` java
    public interface IXxxService extends IBaseService<Xxx>{}
    ```
* service 实现类应继承 AbstractBaseServiceAdapter<T>，实现对应接口，并指明泛类
    ``` java
    public class XxxServiceImpl extends AbstractBaseServiceAdapter<Xxx> 
        implements IXxxService {}
    ```
* AbstractBaseServiceAdapter 类实现了 IBaseService 接口中的方法，并提供 frameDaoImpl 实例操作数据库。如果该类中没有提供所需方法，可以使用 frameDaoImpl 提供的方法直接调用 mapper的方法。
* flyway 版本号从0.0.1开始，规则为V0.0.1_1__XXXX.sql，1代表这个版本的脚本顺序，xxxx代表文件执行了哪些操作

MybatisGenerator:

You can pass parameters to the goal with standard Maven command line properties. For example:

mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate