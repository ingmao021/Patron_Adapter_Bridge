package com.iluwatar.adapter.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Concrete Implementor for the Bridge pattern.
 * Propels the boat using a motor engine.
 */
public class MotorPropulsion implements PropulsionSystem {

  private static final Logger LOGGER = LoggerFactory.getLogger(MotorPropulsion.class);

  @Override
  public void propel() {
    LOGGER.info("The boat is being propelled by motor");
  }
}
