# slf4j-qslog
基于slf4j的门面模式自定义的日志存储

## 说明
*自己可以自定义日志的存储方式，rpc远程调用或者直接写数据库
*在QsLogger类中可以自己修改代码，例子是http远程调用写入日志
*默认记录warn，error级别的日志，其他日志级需要配置是否开启，日志级别不存在依赖关系

##配置说明
'qslog.url=http://localhost:8081
 qslog.trace=false;
 qslog.debug=false;
 qslog.info=true;
 qslog.warn=false;
 qslog.error=false;'

