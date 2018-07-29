package com.dianping.cat.build;

import com.dianping.cat.CatClientModule;
import com.dianping.cat.configuration.ClientConfigManager;
import com.dianping.cat.configuration.DefaultClientConfigManager;
import com.dianping.cat.message.MessageProducer;
import com.dianping.cat.message.internal.DefaultMessageManager;
import com.dianping.cat.message.internal.DefaultMessageProducer;
import com.dianping.cat.message.internal.MessageIdFactory;
import com.dianping.cat.message.io.LocalLogSender;
import com.dianping.cat.message.io.LogTransportManager;
import com.dianping.cat.message.io.TransportManager;
import com.dianping.cat.message.spi.MessageManager;
import java.util.ArrayList;
import java.util.List;
import org.unidal.initialization.Module;
import org.unidal.lookup.configuration.AbstractResourceConfigurator;
import org.unidal.lookup.configuration.Component;

public class ComponentsConfigurator extends AbstractResourceConfigurator {

  public static void main(String[] args) {
    generatePlexusComponentsXmlFile(new ComponentsConfigurator());
  }

  @Override
  public List<Component> defineComponents() {
    List<Component> all = new ArrayList<Component>();

    all.add(C(ClientConfigManager.class, DefaultClientConfigManager.class));
    all.add(C(MessageIdFactory.class));

    all.add(C(MessageManager.class, DefaultMessageManager.class) //
        .req(ClientConfigManager.class, TransportManager.class, MessageIdFactory.class));
    all.add(C(MessageProducer.class, DefaultMessageProducer.class) //
        .req(MessageManager.class, MessageIdFactory.class));

//		all.add(C(TcpSocketSender.class) //
//		      .req(TcpSocketSender.class, MessageIdFactory.class) //
//		      .req(MessageStatistics.class, "default", "m_statistics") //
//				.req(MessageCodec.class, PlainTextMessageCodec.ID, "m_codec"));
//		all.add(C(TransportManager.class, DefaultTransportManager.class) //
//		      .req(ClientConfigManager.class, TcpSocketSender.class));

    all.add(C(LocalLogSender.class));
    all.add(C(TransportManager.class, LogTransportManager.class)
        .req(LocalLogSender.class));

//		all.add(C(MessageStatistics.class, DefaultMessageStatistics.class));
//		all.add(C(StatusUpdateTask.class) //
//				.req(MessageStatistics.class, ClientConfigManager.class));

    all.add(C(Module.class, CatClientModule.ID, CatClientModule.class));

    all.addAll(new CodecComponentConfigurator().defineComponents());

    return all;
  }
}