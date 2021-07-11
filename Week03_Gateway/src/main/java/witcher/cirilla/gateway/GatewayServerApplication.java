package witcher.cirilla.gateway;

import witcher.cirilla.gateway.inbound.HttpInboundServer;

import java.util.Arrays;

public class GatewayServerApplication {

    public static void main(String[] args) {
        String proxyServers = "http://localhost:8801";
        HttpInboundServer server = new HttpInboundServer(8888, Arrays.asList(proxyServers.split(",")));
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
