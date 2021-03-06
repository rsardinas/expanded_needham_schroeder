package tcp.clients;
import java.util.HashMap;

import protocols.*;
import tcp.*;
import tcp.servers.*;

public class Alice extends TcpClient {
  private static HashMap<String, Integer> hosts;
  public static Integer PORT = 8879;
  private NeedhamSchroederClientProtocol protocol;
  private boolean expanded = false;
  static {
    hosts = new HashMap<String, Integer>();
    buildHosts();
  }

  public Alice() {
    if(expanded) {
      protocol = new ExpandedNeedhamSchroederClientProtocol();
    }
    else {
      protocol = new NeedhamSchroederClientProtocol();
    }
  }

  public Integer getPort() {
    return PORT;
  }

  private static void buildHosts() {
    hosts.put("Bob", Bob.PORT);
    hosts.put("KDC", Kdc.PORT);
  }

  public Protocol getProtocol() {
    return protocol;
  }

  public void start() {
    if(expanded) {
      start(hosts.get("Bob"));
    }
    start(hosts.get("KDC"));
    start(hosts.get("Bob"));
  }
}

