package com.pxc.weixin_login_demo.controller;


import org.springframework.stereotype.Component;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocket {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    public static int onlineCount = 0;

    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    public static ConcurrentMap<String, WebSocket> webSocketSet = new ConcurrentHashMap<>();

    private static ConcurrentMap<String, String> sessionSecenId = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

//	private String sceneId;

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(Session session) {

        this.session = session;
//		this.sceneId = sceneId;
        webSocketSet.put(session.getId(), this);     //加入set中
        addOnlineCount();           //在线数加1
         System.out.println("有新连接加入:sessionId = " + session.getId() + "当前在线人数为" + getOnlineCount()+"---- " + webSocketSet.size());
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(Session session) {
        webSocketSet.remove(session.getId());  //从set中删除
        subOnlineCount();           //在线数减1
//		System.out.println("有一连接关闭:sessionId = " + session.getId() + "当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param sceneId 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String sceneId, Session session) {
		System.out.println("来自客户端的消息: sceneId = " + sceneId);
        sessionSecenId.put(sceneId, session.getId());
//		try {
//			//给指定客户端发送消息
//			webSocketSet.get(session.getId()).sendMessage(sceneId);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        //群发消息
//		for(WebSocket item: webSocketSet){
//			try {
//				item.sendMessage(message);
//			} catch (IOException e) {
//				e.printStackTrace();
//				continue;
//			}
//		}
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error   java.io.EOFException
     *                at org.apache.tomcat.util.net.NioEndpoint$NioSocketWrapper.fillReadBuffer(NioEndpoint.java:1244)
     *                at org.apache.tomcat.util.net.NioEndpoint$NioSocketWrapper.read(NioEndpoint.java:1184)
     *                at org.apache.tomcat.websocket.server.WsFrameServer.onDataAvailable(WsFrameServer.java:72)
     *                at org.apache.tomcat.websocket.server.WsFrameServer.doOnDataAvailable(WsFrameServer.java:171)
     *                at org.apache.tomcat.websocket.server.WsFrameServer.notifyDataAvailable(WsFrameServer.java:151)
     *                at org.apache.tomcat.websocket.server.WsHttpUpgradeHandler.upgradeDispatch(WsHttpUpgradeHandler.java:148)
     *                at org.apache.coyote.http11.upgrade.UpgradeProcessorInternal.dispatch(UpgradeProcessorInternal.java:54)
     *                at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:53)
     *                at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:868)
     *                at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1457)
     *                at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
     *                at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
     *                at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
     *                at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
     *                at java.lang.Thread.run(Thread.java:745)
     *                <p/>
     *                WEBSOCKET超时异常,tomcat报出,nginx上面的配置。
     *                <p/>
     *                location /main/websocket {
     *                proxy_pass                http://websoket;
     *                proxy_set_header          Host $host:$server_port;
     *                proxy_http_version        1.1;
     *                proxy_set_header          Upgrade $http_upgrade;
     *                proxy_set_header          Connection "upgrade";
     *                proxy_read_timeout        2100;
     *                proxy_connect_timeout 2100;
     *                proxy_send_timeout 2100;
     *                }
     */
    @OnError
    public void onError(Session session, Throwable error) {
        try {
            session.close();
            System.out.println("发生异常：关闭session id = " + session.getId());
            webSocketSet.remove(session.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     *
     * @param sceneId
     * @throws IOException
     */
    public static void sendMessage(String sceneId, boolean valid) throws IOException {
        WebSocket webSocket = webSocketSet.get(sessionSecenId.get(sceneId));
        if (webSocket != null && webSocket.session.isOpen()) {
            Session session = webSocket.session;
            session.getBasicRemote().sendText(String.valueOf(valid));
            webSocketSet.remove(session.getId());
            session.close();
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocket.onlineCount--;
    }
}