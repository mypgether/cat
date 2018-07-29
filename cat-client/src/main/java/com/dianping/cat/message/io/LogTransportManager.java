package com.dianping.cat.message.io;

import com.dianping.cat.configuration.ClientConfigManager;
import org.unidal.lookup.annotation.Inject;

public class LogTransportManager implements TransportManager {

  @Inject
  private LocalLogSender m_localLogSender;
  @Inject
  private ClientConfigManager m_manager;

  @Override
  public MessageSender getSender() {
    return m_localLogSender;
  }
}