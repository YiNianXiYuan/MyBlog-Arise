# Arise Blog
## 个人博客系统
Arise Blog是由SpringBoot1.5 + MyBatis + Thymeleaf等技术实现的个人网站
### 适用对象
* Spring Boot 初学者。
* 与作者一样，使用 hexo 但苦于没有后台管理工具（界面）管理文章的写作者。该博客系统模仿 hexo 生成的访问路径，并支持 markdown 文件导入功能。
* 懵懂者。初次接触博客系统的人。
### 技术栈
#### 后端
* 核心框架：SpringBoot
* 持久层框架：MyBatis
* 模板框架：Thymeleaf
* 分页插件：PageHelper
* 缓存框架：Ehcache
* Markdown：Commonmark

#### 前端
* JS框架：Jquery
* CSS框架：Bootstrap
* 富文本编辑器：editor.md
* 文件上传：dropzone
* 弹框插件：sweetalert

#### 第三方
* 七牛云（文件上传）
* 百度统计

### 预览效果
#### 前端效果
![index](https://github.com/YiNianXiYuan/MyBlog-Arise/tree/master/blogImage/index.png)

![archives](https://github.com/YiNianXiYuan/MyBlog-Arise/tree/master/blogImage/archives.png)

![detail](https://github.com/YiNianXiYuan/MyBlog-Arise/tree/master/blogImage/detail.png)

![category](https://github.com/YiNianXiYuan/MyBlog-Arise/tree/master/blogImage/category.png)

![about](https://github.com/YiNianXiYuan/MyBlog-Arise/tree/master/blogImage/about.png)

#### 后端效果

![adminlogin](https://github.com/YiNianXiYuan/MyBlog-Arise/tree/master/blogImage/adminLogin.png)

![adminindex](https://github.com/YiNianXiYuan/MyBlog-Arise/tree/master/blogImage/adminIndex.png)

![articlepublish](https://github.com/YiNianXiYuan/MyBlog-Arise/tree/master/blogImage/articlePublish.png)

![articlemanager](https://github.com/YiNianXiYuan/MyBlog-Arise/tree/master/blogImage/articleManager.png)

![filemanager](https://github.com/YiNianXiYuan/MyBlog-Arise/tree/master/blogImage/fileManager.png)

![setting](https://github.com/YiNianXiYuan/MyBlog-Arise/tree/master/blogImage/setting.png)

### 安装
下载源码，执行sql文件，然后修改application-dev.yml文件中连接数据库的用户名、密码。运行项目即可。

前端访问地址：http://localhost:8888

后台访问地址：http://localhost:8888/admin 用户名：admin 密码：123456

