package com.iluwatar.adapter.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Concrete Implementor for the Bridge pattern.
 * Propels the boat using sails.
 */
public class SailPropulsion implements PropulsionSystem {

  private static final Logger LOGGER = LoggerFactory.getLogger(SailPropulsion.class);

  @Override
  public void propel() {
    LOGGER.info("The boat is being propelled by sails");
  }
}
