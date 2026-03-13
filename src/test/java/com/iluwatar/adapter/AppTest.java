
package com.iluwatar.adapter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

/** Tests that Adapter example runs without errors. */
class AppTest {

  /** Check whether the execution of the main method in {@link App} throws an exception. */
  @Test
  void shouldExecuteApplicationWithoutException() {

    assertDoesNotThrow(() -> App.main(new String[] {}));
  }
}
