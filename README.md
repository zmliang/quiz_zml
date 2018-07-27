#####
项目包括服务后端和android前端app，后端部署在阿里云服务器上，采用nginx转发http请求
服务端：game-server，spring+netty+mybatis；提供的服务有用户信息查询，登录验证密码，
		修改用户信息，上传用户头像并保存到服务器， websocket连接获取答题题库信息；
		
客户端：myQuiz，mvp+retrofit+rxjava；图片处理框架Glide加载网络图片；包含的功能有
		新闻(爬取QQ新闻，解析显示)，图片(抓包百度图片http请求，获取图片URL并显示)
		网络答题功能(类似头脑王者);用户信息修改，上传头像等功能
