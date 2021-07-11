package witcher.cirilla.gateway.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

public class HttpClientInboundHandler extends ChannelInboundHandlerAdapter {

    // 客户端与服务端连接成功时，触发
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        URI uri = new URI("/");
        FullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri.toASCIIString());
        request.headers().add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        // 过滤 header
        request.headers().add("mao", "soul");
        request.headers().add(HttpHeaderNames.CONTENT_LENGTH, request.content().readableBytes());
        ctx.writeAndFlush(request);
    }

    // 客户端读取服务端发送的数据
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("msg: " + msg);
        if (msg instanceof FullHttpResponse) {
            FullHttpResponse response = (FullHttpResponse) msg;

            HttpHeaders httpHeaders = response.headers();
            System.out.println("start print Gateway header: =====================");
            System.out.println("kk: " + httpHeaders.get("kk"));
            System.out.println("end print Gateway header: =====================");

            System.out.println("\r\n");

            ByteBuf buf = response.content();
            System.out.println("start print Server response: =====================");
            System.out.println(buf.toString(CharsetUtil.UTF_8));
            System.out.println("end print Server response: =====================");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
