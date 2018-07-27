1.comment是后台管理系统程序，因为是maven项目，
	eclipse 和 idea以及其他IDE 均可导入。
2.react-dianping是前端项目初始代码，已经删除了node_modules，
	需要安装node.js环境后重新执行npm install，具体步骤见视频。
	该目录下webpack.config.js文件中 "target: 'http://127.0.0.1:8081/comment',"是指向后台，
	如果想要前端app连接mock server ，注释该句，放出上面的“target: 'http://localhost:3000',”
	修改时注意输入法是英文输入法（切忌中文）
3.SQL脚本请先运行createTable.sql建表，再运行loadInitData.sql插入数据