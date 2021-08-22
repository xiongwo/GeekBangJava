package io.kimmking.rpcfx.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

public class HttpClientInboundHandler extends ChannelInboundHandlerAdapter {

    private String response;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpResponse) {
            HttpResponse response = (HttpResponse) msg;
            System.out.println("CONTENT_TYPE:" + response.headers().get(HttpHeaders.Names.CONTENT_TYPE));
        }
        if(msg instanceof HttpContent) {
            HttpContent content = (HttpContent) msg;
            ByteBuf buf = content.content();
            response = buf.toString(io.netty.util.CharsetUtil.UTF_8);
            System.out.println(response);
            buf.release();
        }
    }

    public String getResponse() {
        return this.response;
    }
}
