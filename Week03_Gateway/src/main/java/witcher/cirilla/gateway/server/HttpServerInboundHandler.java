package witcher.cirilla.gateway.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import static io.netty.buffer.Unpooled.copiedBuffer;

public class HttpServerInboundHandler extends SimpleChannelInboundHandler<HttpObject> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject httpObject) throws Exception {
        if (httpObject instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) httpObject;

            StringBuilder responseContent = new StringBuilder();
            responseContent.append("VERSION: ");
            responseContent.append(httpRequest.protocolVersion().text());
            responseContent.append("\r\n");
            responseContent.append("REQUEST_URI: ");
            responseContent.append(httpRequest.uri());
            responseContent.append("\r\n");
            responseContent.append("Content: ");
            responseContent.append("Hello, I am from Server\r\n");

            ByteBuf buf = copiedBuffer(responseContent, CharsetUtil.UTF_8);

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
            response.headers().set("Content-Type", "text/plain; charset=UTF-8");
            response.headers().set("Content-Length", buf.readableBytes());

            channelHandlerContext.writeAndFlush(response);
        }
    }

}
