package org.freeswitch.esl.client;

import com.google.common.base.Throwables;
import org.freeswitch.esl.client.inbound.Client;
import org.freeswitch.esl.client.internal.IModEslApi.EventFormat;
import org.freeswitch.esl.client.transport.SendMsg;
import org.freeswitch.esl.client.transport.message.EslMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class ClientExample {
    private static final Logger L = LoggerFactory.getLogger(ClientExample.class);

    public static void main(String[] args) {
        try {
            String password = "ClueCon";

            Client client = new Client();

            client.addEventListener((ctx, event) -> L.info("Received event: {}", event.getEventName()));

            client.connect(new InetSocketAddress("192.168.137.50", 8021), password, 10);
            client.setEventSubscriptions(EventFormat.JSON, "ALL");
            EslMessage eslMessage = client.sendApiCommand("show","calls as json");

            System.out.println(eslMessage.getBodyLines().toString());

        } catch (Throwable t) {
            Throwables.propagate(t);
        }
    }
}
