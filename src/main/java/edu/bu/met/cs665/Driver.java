/**
 * Name: Tiansheng Fu
 * Course: CS-665 Software Design & Patterns
 * Date: 10/19/2026
 * File Name: Driver.java
 * Description: Represents a driver receiving delivery notifications.
 * Observer pattern: https://refactoring.guru/design-patterns/observer/java/example
 * Java unmodifiable list API: https://docs.oracle.com/javase/8/docs/api/java/util/Collections.html#unmodifiableList-java.util.List-
 */

package edu.bu.met.cs665;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Implements the notification contract defined by the Observer pattern. */
public class Driver implements DeliveryObserver {
  private final String driverId;
  private boolean available = true;
  private final List<DeliveryRequest> receivedRequests = new ArrayList<>();

  /** Creates a driver with a valid identifier. */
  public Driver(String driverId) {
    if (driverId == null || driverId.trim().isEmpty()) {
      throw new IllegalArgumentException("Driver ID must not be blank.");
    }
    this.driverId = driverId;
  }

  /** Returns the driver's identifier. */
  public String getDriverId() {
    return driverId;
  }

  /** Indicates whether the driver can receive delivery requests. */
  public boolean isAvailable() {
    return available;
  }

  /** Updates availability so the notification service can filter drivers. */
  public void setAvailable(boolean available) {
    this.available = available;
  }

  /** Records a delivery request when the driver is notified. */
  @Override
  public void notifyDelivery(DeliveryRequest request) {
    receivedRequests.add(request);
  }

  /** Prevents callers from modifying the driver's notification history. */
  public List<DeliveryRequest> getReceivedRequests() {
    return Collections.unmodifiableList(receivedRequests);
  }
}
