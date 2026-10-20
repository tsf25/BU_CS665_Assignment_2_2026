/**
 * Name: Tiansheng Fu
 * Course: CS-665 Software Design & Patterns
 * Date: 10/19/2026
 * File Name: DeliveryNotificationService.java
 * Description: Broadcasts delivery requests to available drivers.
 * Observer pattern: https://refactoring.guru/design-patterns/observer/java/example
 */

package edu.bu.met.cs665;

/** Sends each delivery request to all registered available drivers. */
public class DeliveryNotificationService {
  private final Shop shop;

  /** Creates a notification service for the specified shop. */
  public DeliveryNotificationService(Shop shop) {
    if (shop == null) {
      throw new IllegalArgumentException("Shop must not be null.");
    }
    this.shop = shop;
  }

  /** Notifies all registered drivers who are available. */
  public void broadcastDeliveryRequest(DeliveryRequest request) {
    if (request == null) {
      throw new IllegalArgumentException("Request must not be null.");
    }

    for (Driver driver : shop.getDrivers()) {
      if (driver.isAvailable()) {
        driver.notifyDelivery(request);
      }
    }
  }
}
