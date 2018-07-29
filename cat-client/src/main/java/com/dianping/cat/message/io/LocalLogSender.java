package com.dianping.cat.message.io;

import com.dianping.cat.message.spi.MessageTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalLogSender implements MessageSender {

  public final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public void initialize() {

  }

  @Override
  public void send(MessageTree tree) {
    logger.info(tree.toString());
  }

  @Override
  public void shutdown() {

  }
}
