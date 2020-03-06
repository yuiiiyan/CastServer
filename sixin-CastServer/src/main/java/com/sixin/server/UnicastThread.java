package com.sixin.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 服务器单播信息接收与发送处理
 * @author shuofang
 *弃用
 */
public class UnicastThread extends Thread {

	private  int Port = 8900;
	private Logger logger = LoggerFactory.getLogger(UnicastThread.class);
	ServerBootstrap bootstrap;
	public UnicastThread(int port) {
		super();
		Port = port;
	}
	@Override
	public void run() {
		// Server服务启动器  
		 bootstrap = new ServerBootstrap(); 
		EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			bootstrap.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
				//可以在socket接上来的时候添加很多指定义逻辑
				//ch.pipeline().addLast("encode",new StringEncoder()); 
				//ch.pipeline().addLast("decode",new StringDecoder()); 
				ch.pipeline().addLast("ping", new IdleStateHandler(25, 15, 10,TimeUnit.SECONDS));
				ch.pipeline().addLast(new UnicastHandler());
				}
			}).option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
			ChannelFuture future = bootstrap.bind(Port).sync();
			System.out.println("开始监听 "+ Port);
			future.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	/**
	 * @return the bootstrap
	 */
	public ServerBootstrap getBootstrap() {
		return bootstrap;
	}
	/**
	 * @param bootstrap the bootstrap to set
	 */
	public void setBootstrap(ServerBootstrap bootstrap) {
		this.bootstrap = bootstrap;
	}
	
}
