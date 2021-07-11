package witcher.cirilla.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public boolean filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        return fullRequest.headers().contains("mao")
                && fullRequest.headers().get("mao").equals("soul");
    }
}
