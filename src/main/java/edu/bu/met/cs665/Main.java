/**
 * Name: Tiansheng Fu
 * Course: CS-665 Software Design & Patterns
 * Date: 10/19/2026
 * File Name: Main.java
 * Description: Demonstrates a delivery notification.
 */

package edu.bu.met.cs665;

public class Main {

  /** Demonstrates one delivery request sent to one available driver.*/
  public static void main(String[] args) {
    Shop shop = new Shop();
    Driver driver = new Driver("D1");
    shop.registerDriver(driver);

    DeliveryRequest request =
        new DeliveryRequest("REQ-001", "100 Main Street", "Customer package");

    DeliveryNotificationService service =
        new DeliveryNotificationService(shop);
    service.broadcastDeliveryRequest(request);

    System.out.println("Notifications received: "
        + driver.getReceivedRequests().size());
  }
}
