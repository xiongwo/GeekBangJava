package witcher.cirilla.gateway.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;

public class NettyClientApplication {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<Channel>() {
                        //当前该方法监听channel是否初始化
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new HttpClientCodec());
                            channel.pipeline().addLast(new HttpObjectAggregator(65536));
                            channel.pipeline().addLast(new HttpContentDecompressor());
                            channel.pipeline().addLast(new HttpClientInboundHandler());
                        }
                    });

            Channel channel = bootstrap.connect("127.0.0.1", 8888).sync().channel();
            channel.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

}
