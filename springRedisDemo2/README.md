# springRedisDemo2
    使用第二种方式集成，在dao层实现代码更精简；
    在项目中遇到的问题：
    1.
    <dependency>
        <groupId>commons-logging</groupId>
        <artifactId>commons-logging</artifactId>
        <version>1.1.1</version>
    </dependency>
    <!--<dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>1.7.10</version>
    </dependency>-->
    
    在运行测试时，org.slf4j会报错，因为本地已经自动导入1.7.10。
    于是我删除了slf4j但是仍然报错。经网上查阅之后，
    导入commons-logging 之后正常运行。