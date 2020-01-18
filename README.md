# 个人博客
本项目已部署到服务器，可以通过[`http://121.36.49.167:8080/login`](http://121.36.49.167:8080/login)访问。

**update**
为了节省服务器资源，已结束该进程

## 1. Linux环境下安装说明
### 1.1 使用源码安装

本项目使用**Git**进行代码版本管理，所有的源代码已经放在了**GitHub**上（**GitHub**地址：[https://github.com/Practicum-in-Applied-Software/Personal-blog](https://github.com/Practicum-in-Applied-Software/Personal-blog)），可以将代码`clone`下来下来后直接运行。由于本项目使用**IntelliJ IDEA**作为主要的开发工具，所以也推荐使用**IntelliJ IDEA**运行本项目。

在将本项目`clone`下来后会有一个名称为`Personal-blog`的文件夹，使用**IntelliJ IDEA**打开该文件夹，找到文件名为`application.properties`的文件（文件的路径为`PersonalBlog/src/main/resources/application.properties`）,该文件是本项目的配置文件，原有的配置如下

```
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/personalblog?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8
spring.datasource.username=root
spring.datasource.password=3306

# 打印sql语句
logging.level.com.example.demo.mapper=debug
```

其中`server.port`是本项目的端口号，可以根据自己的需求更改。`spring.datasource.url`是数据库的地址，`localhost`是主机名称，可以自行更改，`personalblog`是数据库的名称，也可以自行更改。`spring.datasource.username`是登陆数据库时的用户名，`spring.datasource.password`是用户的密码（需要保证登陆数据库的用户名和密码正确以及登陆的用户具有访问该数据库的权限）。`logging.level.com.example.demo.mapper=debug`用于在控制台输出运行时执行的**sql**语句，方便**debug**，如果不需要可以删除。

在所有的配置（例如数据库配置，**java**配置等）均正确的情况下，运行该项目后在浏览器中输入`localhost:8080/login`即可进入登陆页面（其中主机名和端口号需要根据配置文件中的配置而定）。

### 1.2 使用jar包安装

在使用**IntelliJ IDEA**打开本项目后，找到**Maven**窗口，点击**package**可以将该项目打包为**jar**包（如下图所示），默认打包后的名称为`demo-0.0.1-SNAPSHOT.jar`，该**jar**包的相对路径为`target/demo-0.0.1-SNAPSHOT.jar`。

在所有配置均正确的情况下输入如下命令即可运行该项目:
```
java –jar demo-0.0.1-SNAPSHOT.jar
```

如果使用远程的服务器来运行该项目，并且希望在结束与服务器的连接后进程仍然保持运行，则需要使用`nohup`命令让进程在后台运行。详细命令如下：
```
nohup java -jar demo-0.0.1-SNAPSHOT.jar &
```

## 2. Windows环境下安装说明
**Windows**环境下的图形界面比**Linux**对用户更加友好，且**Windows**环境下运行该项目的方式与**Linux**下基本相同，均需要预先配置好**java**环境和**mysql**数据库，然后用**IntelliJ IDEA**打开再运行，在这里就不再赘述。