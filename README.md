# mybatis-plus代码生成maven插件
> 为了在项目中快捷方便的代码生成，将mybatis-plus-generator封装为了一个maven的插件`mybatis-plus-generator-maven-plugin`，在要使用的项目pom文件引入该插件，执行mvn命令，即可直接生成代码到项目中，生成基于`mybatis-plus`的mapper、service、controller三层结构，包括entity实体类和mapper.xml文件，生成后直接能够满足基本的条件查询和分页查询。下面介绍该插件的使用步骤：
``` 
更新记录:
2020-01-11 完善对使用oracle数据源找不到驱动的问题
```
## 一、下载插件
 将源代码导入需要生成代码的项目工程中，执行`mvn intall` 

## 二、在pom中引入插件
在要使用插件的工程pom文件中引入该插件，如下案例
```xml
<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			<!-- mybatis-plus generator 自动生成代码插件 -->
			<plugin>
				<groupId>com.weiller</groupId>
				<artifactId>mybatis-plus-generator-maven-plugin</artifactId>
				<version>1.0.0</version>
				<configuration>
					<configurationFile>${basedir}/src/main/resources/generator/mp-code-generator-config.yaml</configurationFile>
				</configuration>
                                <dependencies>
                                        <!-- 如果是oracle库，需要额外引入jdbc驱动包，mysql则可忽略 -->
					<dependency>
						<groupId>com.oracle</groupId>
						<artifactId>ojdbc6</artifactId>
						<version>${ojdbc.version}</version>
					</dependency>
				</dependencies>
			</plugin>
			<!-- mybatis-plus generator 自动生成代码插件 -->
		</plugins>
	</build>
```
注意`configurationFile`参数为 下一步中配置文件generator-config的位置，该文件类型为`yaml`。
## 三、填写配置文件
配置完整案例
```yaml
globalConfig:
  author: weiller
  open: false
  idType: INPUT
  dateType: ONLY_DATE
  enableCache: false
  activeRecord: false
  baseResultMap: true
  baseColumnList: true
  swagger2: false
  fileOverride: true
dataSourceConfig:
  url: jdbc:mysql://localhost:3306/demo?useUnicode=true&useSSL=false&characterEncoding=utf8
  driverName: com.mysql.jdbc.Driver
  username: root
  password: xiweile
packageConfig:
  parent: com.weiller
  moduleName: rest
  entity: model
  service: service
  serviceImpl: service.impl
  mapper: dao
  xml: mapper
  controller: controller
  pathInfo:
    entity_path: src\main\java\com\weiller\rest\model
    service_path: src\main\java\com\weiller\rest\service
    service_impl_path: src\main\java\com\weiller\rest\service\impl
    mapper_path: src\main\java\com\weiller\rest\dao
    xml_path: src\main\resources\com\weiller\rest\mapper
    controller_path: src\main\java\com\weiller\rest\controller
strategyConfig:
  naming: underline_to_camel
  columnNaming: underline_to_camel
  entityLombokModel: true
  superMapperClass: com.baomidou.mybatisplus.core.mapper.BaseMapper
  superServiceClass: com.baomidou.mybatisplus.extension.service.IService
  superServiceImplClass: com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
  controllerMappingHyphenStyle: true
  restControllerStyle: true
  tablePrefix:
  include:
    - t_user
```
配置项参数解释：[https://mp.baomidou.com/config/generator-config.html#基本配置](https://mp.baomidou.com/config/generator-config.html#%E5%9F%BA%E6%9C%AC%E9%85%8D%E7%BD%AE)
## 四、运行maven命令
在命令工具中，进入到要生成项目的根目录（即pom.xml目录），执行以下命令
```shell
mvn mybatis-plus-generator:generator
```
如果是使用InterlliJ IDEA工具，使用更加方便，步骤如下图：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20191115153431762.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3hpd2VpbGxlcg==,size_16,color_FFFFFF,t_70)
生成结果如下：

![在这里插入图片描述](https://img-blog.csdnimg.cn/20191115152720828.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3hpd2VpbGxlcg==,size_16,color_FFFFFF,t_70)

