# mybatis-plus代码生成maven插件
> 为了在项目中快捷方便的代码生成，将mybatis-plus-generator封装为了一个maven的插件`mybatis-plus-generator-maven-plugin`，在要使用的项目pom文件引入该插件，执行mvn命令，即可直接生成代码到项目中，生成基于`mybatis-plus`的mapper、service、controller三层结构，包括entity实体类和mapper.xml文件，生成后直接能够满足基本的条件查询和分页查询。下面介绍该插件的使用步骤：
``` 
更新记录:
完善对使用oracle数据源找不到驱动的问题
```
## 一、下载插件
 将源代码导入需要生成代码的项目工程中，执行`mvn intall` 当然已经上传maven中央仓库了

## 二、在pom中引入插件
在要使用插件的工程pom文件中引入该插件，如下案例
```xml
    <build>
        <plugins>
            <!-- mybatis-plus generator 自动生成代码插件 -->
            <plugin>
                <groupId>com.github.ciweigg</groupId>
                <artifactId>mybatis-plus-generator-maven-plugin</artifactId>
                <version>1.0.1-RELEASE</version>
                <configuration>
                    <configurationFile>${basedir}/src/main/resources/generator/mp-code-generator-config.yaml
                    </configurationFile>
                </configuration>
                <dependencies>
                    <!-- 如果是oracle库，需要额外引入jdbc驱动包，mysql则可忽略 -->
                    <dependency>
                        <groupId>com.oracle.ojdbc</groupId>
                        <artifactId>ojdbc8</artifactId>
                            <!--springboot可以直接使用${ojdbc.version} 否则需要指定版本-->
                        <!--<version>${ojdbc.version}</version>-->
                        <version>19.3.0.0</version>
                    </dependency>
                </dependencies>
            </plugin>
            <!-- mybatis-plus generator 自动生成代码插件 -->
        </plugins>
    </build>
```
注意`configurationFile`参数为 下一步中配置文件generator-config的位置，该文件类型为`yaml`
## 三、填写配置文件
配置完整案例
```yaml
globalConfig:
  # 作者
  author: Una Ma
  # 生成完是否打开
  open: false
  # 指定生成的主键的ID类型 默认值：null
  idType: INPUT
  # 时间类型对应策略 默认值：TIME_PACK
  dateType: ONLY_DATE
  # 是否在xml中添加二级缓存配置 默认值：false
  enableCache: false
  # 开启 ActiveRecord 模式 默认值：false
  activeRecord: false
  # 开启 BaseResultMap 默认值：false
  baseResultMap: true
  # 开启 baseColumnList 默认值：false
  baseColumnList: true
  # 开启 swagger2 模式 默认值：false
  swagger2: false
  # 是否覆盖已有文件 默认值：false
  fileOverride: true
dataSourceConfig:
  # 数据库连接地址
  url: jdbc:mysql://127.0.0.1:3309/bl2?useUnicode=true&useSSL=false&characterEncoding=utf8
  # 数据库驱动
  driverName: com.mysql.jdbc.Driver
  # 数据库用户名
  username: root
  # 数据库密码
  password: 123456
packageConfig:
  # 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
  parent: com.una.product
  # 父包模块名
#  moduleName: api
  # Entity包名
  entity: mybatis.model
  # Service包名
  service: mybatis.service
  # Service Impl包名
  serviceImpl: mybatis.service.impl
  # Mapper包名
  mapper: mybatis.mapper
  # Mapper XML包名
  xml: mapper
  # Controller包名
  controller: controller
  # 路径配置信息
  pathInfo:
    entity_path: ./src/main/java/com/una/product/mybatis/model
    service_path: ./src/main/java/com/una/product/mybatis/service
    service_impl_path: ./src/main/java/com/una/product/mybatis/service/impl
    mapper_path: ./src/main/java/com/una/product/mybatis/mapper
    xml_path: ./src/main/resources/mapper
    controller_path: ./src/main/java/com/una/product/controller
# 是否生成下面文件 放开不生成 注释默认生成
templateConfig:
#  entity:
#  service:
#  serviceImpl:
#  mapper:
#  xml:
#  controller:
# 默认使用vm 需要哪个开启哪个 都不设置默认vm 建议使用vm 可以控制controller service model等是否生成
#useftl: true
#usebtl: true
usevm: true
strategyConfig:
  # 数据库表映射到实体的命名策略
  naming: underline_to_camel
  # 数据库表字段映射到实体的命名策略, 未指定按照 naming 执行
  columnNaming: underline_to_camel
  # 【实体】是否为lombok模型（默认 false）
  entityLombokModel: true
  # 自定义继承的Mapper类全称，带包名
  superMapperClass: com.baomidou.mybatisplus.core.mapper.BaseMapper
  # 自定义继承的Service类全称，带包名
  superServiceClass: com.baomidou.mybatisplus.extension.service.IService
  # 自定义继承的ServiceImpl类全称，带包名
  superServiceImplClass: com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
  # 驼峰转连字符 false接口请求地址为驼峰 true的话会用-连接
  controllerMappingHyphenStyle: false
  # 生成 @RestController 控制器
  restControllerStyle: true
  # 表前缀
  tablePrefix:
  # 是否生成实体时，生成字段注解
  entityTableFieldAnnotationEnable: true
  # 【实体】是否生成字段常量（默认 false)
  entityColumnConstant: true
  # 需要包含的表名，允许正则表达式（与exclude二选一配置)
  include:
    - business_license
```
配置项参数解释：[https://mp.baomidou.com/config/generator-config.html#基本配置](https://mp.baomidou.com/config/generator-config.html#%E5%9F%BA%E6%9C%AC%E9%85%8D%E7%BD%AE)
## 四、运行maven命令
在命令工具中，进入到要生成项目的根目录（即pom.xml目录），执行以下命令
```shell
mvn mybatis-plus-generator:generator
```
如果是使用InterlliJ IDEA工具，使用更加方便

可以直接选择右边的Maven中的Plugins选择mybatis-plus-generator:generator就行了

> 插件不支持读取外部自定义模板 如果需要自定义模板需要替换jar中的templates中的文件
