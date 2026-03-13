
package com.iluwatar.adapter;

import com.iluwatar.adapter.bridge.Boat;
import com.iluwatar.adapter.bridge.CargoBoat;
import com.iluwatar.adapter.bridge.MotorPropulsion;
import com.iluwatar.adapter.bridge.PassengerBoat;
import com.iluwatar.adapter.bridge.SailPropulsion;


public final class App {

  private App() {}

  /**
   * Program entry point.
   *
   * @param args command line args
   */
  public static void main(final String[] args) {
    // --- Adapter Pattern ---
    // The captain can only operate rowing boats but with adapter he is able to
    // use fishing boats as well
    var captain = new Captain(new FishingBoatAdapter());
    captain.row();

    // --- Bridge Pattern ---
    // The boat abstraction is decoupled from the propulsion implementation.
    // A passenger boat with sail propulsion
    Boat passengerBoat = new PassengerBoat(new SailPropulsion());
    passengerBoat.move();

    // A cargo boat with motor propulsion
    Boat cargoBoat = new CargoBoat(new MotorPropulsion());
    cargoBoat.move();
  }
}
