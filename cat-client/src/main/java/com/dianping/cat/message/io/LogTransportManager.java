package com.dianping.cat.message.io;

import org.unidal.lookup.annotation.Inject;

public class LogTransportManager implements TransportManager {

  @Inject
  private LocalLogSender m_localLogSender;

  @Override
  public MessageSender getSender() {
    return m_localLogSender;
  }
}