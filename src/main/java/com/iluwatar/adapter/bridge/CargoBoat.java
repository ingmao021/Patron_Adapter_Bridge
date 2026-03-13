package com.iluwatar.adapter.bridge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Refined Abstraction for the Bridge pattern.
 * A cargo boat that transports goods.
 */
public class CargoBoat extends Boat {

  private static final Logger LOGGER = LoggerFactory.getLogger(CargoBoat.class);

  public CargoBoat(PropulsionSystem propulsionSystem) {
    super(propulsionSystem);
  }

  @Override
  public void move() {
    LOGGER.info("The cargo boat is moving");
    propel();
  }
}
