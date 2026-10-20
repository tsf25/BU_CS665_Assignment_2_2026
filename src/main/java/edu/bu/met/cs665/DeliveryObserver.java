/**
 * Name: Tiansheng Fu
 * Course: CS-665 Software Design & Patterns
 * Date: 10/19/2026
 * File Name: DeliveryObserver.java
 * Description: Defines the delivery notification observer interface.
 * Observer pattern: https://refactoring.guru/design-patterns/observer/java/example
 * Java interface: https://docs.oracle.com/javase/tutorial/java/IandI/createinterface.html
 */

package edu.bu.met.cs665;

/** Defines the notification behavior for delivery observers. */
public interface DeliveryObserver {

  /** Notifies the observer about a delivery request. */
  void notifyDelivery(DeliveryRequest request);
}
