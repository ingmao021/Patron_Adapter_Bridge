package com.iluwatar.adapter.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Refined Abstraction for the Bridge pattern.
 * A passenger boat that transports people.
 */
public class PassengerBoat extends Boat {

  private static final Logger LOGGER = LoggerFactory.getLogger(PassengerBoat.class);

  public PassengerBoat(PropulsionSystem propulsionSystem) {
    super(propulsionSystem);
  }

  @Override
  public void move() {
    LOGGER.info("The passenger boat is moving");
    propel();
  }
}
