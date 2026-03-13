package com.iluwatar.adapter.bridge;

/**
 * Abstraction for the Bridge pattern.
 * Represents a boat that uses a {@link PropulsionSystem} to move.
 * The abstraction (boat type) is decoupled from the implementation (propulsion system).
 */
public abstract class Boat {

  protected final PropulsionSystem propulsionSystem;

  protected Boat(PropulsionSystem propulsionSystem) {
    this.propulsionSystem = propulsionSystem;
  }

  /**
   * Moves the boat. Each refined abstraction defines its own behavior.
   */
  public abstract void move();

  /**
   * Delegates propulsion to the implementor.
   */
  protected void propel() {
    propulsionSystem.propel();
  }
}
