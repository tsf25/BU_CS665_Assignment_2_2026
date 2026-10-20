/**
 * Name: Tiansheng Fu
 * Course: CS-665 Software Design & Patterns
 * Date: 10/19/2026
 * File Name: Shop.java
 * Description: Registers drivers and broadcasts delivery requests.
 * Observer pattern: https://refactoring.guru/design-patterns/observer/java/example
 * Java List API: https://docs.oracle.com/javase/8/docs/api/java/util/List.html
 */

package edu.bu.met.cs665;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Shop {
  private final List<Driver> drivers = new ArrayList<>();

  /** Registers a driver without creating duplicate registrations. */
  public void registerDriver(Driver driver) {
    Objects.requireNonNull(driver, "driver");
    if (!drivers.contains(driver)) {
      drivers.add(driver);
    }
  }

  /** Removes a registered driver. */
  public void removeDriver(Driver driver) {
    drivers.remove(driver);
  }

  /** Returns an unmodifiable view of the registered drivers. */
  public List<Driver> getDrivers() {
    return Collections.unmodifiableList(drivers);
  }
}
