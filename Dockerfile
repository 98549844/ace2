FROM unitfinance/jdk17-sbt-scala:latest
MAINTAINER Garlam Au
#VOLUME /tmp
ADD target/ace-1.2.jar ace.jar
EXPOSE 8088
# 暴露端口 与配置文件对应
ENTRYPOINT ["java","-jar","ace.jar","--spring.profiles.active=docker"]
# 激活名为 appliction-docker.yml 或 appliction-docker.properties 的配置
#java -jar ace-1.2.jar --spring.profiles.active=uat



#FROM            #基础镜像,一切从这里开始构建.
#MAINTAINER      #镜像是谁写的,姓名+邮箱
#RUN             #镜像构建的时候需要运行的命令
#ADD             #步骤:tomcat镜像,这个tomcat压缩包!添加内容
#WORKDIR         #镜像的工作目录
#VOLUME          #挂载的目录
#EXPOST          #保留端口配置
#CMD             #指定这个容器启动的时候要运行的命令,只有一个会生效,可被替代.
#ENTRYPOINT      #指定这个容器启动的时候要运行的命令,可以追加命令.
#ONBUILD         #当构建一个被集成DockerFile这个时候就会运行ONBUILD的指令.触发指令.
#COPY            #类似ADD,将我们文件拷贝到镜像中.
#ENV             #构建的时候设置环境变量.