/**
 * Name: Tiansheng Fu
 * Course: CS-665 Software Design & Patterns
 * Date: 10/19/2026
 * File Name: DeliverySystemTest.java
 * Description: Tests delivery notifications and driver registration.
 * JUnit 4 Assert methods: https://junit.org/junit4/javadoc/4.13/org/junit/Assert.html
 * Professor example: https://github.com/edorsini/cs-665-project-template/blob/main/src/test/java/edu/bu/met/cs665/TestAge.java
 */

package edu.bu.met.cs665;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;


public class DeliverySystemTest {

  @Test
  public void testBroadcastNotifiesFiveDrivers() {
    Shop shop = new Shop();
    Driver[] drivers = {new Driver("VAN-1"), new Driver("TAXI-1"),
        new Driver("SCOOTER-1"), new Driver("VAN-2"), new Driver("SCOOTER-2")};

    for (Driver driver : drivers) {
      shop.registerDriver(driver);
    }

    DeliveryRequest request = newRequest("REQ-001");
    broadcast(shop, request);

    for (Driver driver : drivers) {
      assertEquals(1, driver.getReceivedRequests().size());
      assertSame(request, driver.getReceivedRequests().get(0));
    }
  }

  @Test
  public void testUnavailableDriverIsNotNotified() {
    Shop shop = new Shop();
    Driver driver = new Driver("D1");
    driver.setAvailable(false);
    shop.registerDriver(driver);
    broadcast(shop, newRequest("REQ-002"));

    assertTrue(driver.getReceivedRequests().isEmpty());
  }

  @Test
  public void testDuplicateRegistration() {
    Shop shop = new Shop();
    Driver driver = new Driver("D1");
    shop.registerDriver(driver);
    shop.registerDriver(driver);
    broadcast(shop, newRequest("REQ-003"));

    assertEquals(1, shop.getDrivers().size());
    assertEquals(1, driver.getReceivedRequests().size());
  }

  @Test
  public void testRemovedDriverIsNotNotified() {
    Shop shop = new Shop();
    Driver driver = new Driver("D1");
    shop.registerDriver(driver);
    shop.removeDriver(driver);
    broadcast(shop, newRequest("REQ-004"));

    assertTrue(driver.getReceivedRequests().isEmpty());
  }

  @Test
  public void testDriverReceivesMultipleRequests() {
    Shop shop = new Shop();
    Driver driver = new Driver("SCOOTER-1");
    shop.registerDriver(driver);
    DeliveryRequest first = newRequest("REQ-005");
    DeliveryRequest second = newRequest("REQ-006");
    broadcast(shop, first);
    broadcast(shop, second);

    assertEquals(2, driver.getReceivedRequests().size());
    assertSame(first, driver.getReceivedRequests().get(0));
    assertSame(second, driver.getReceivedRequests().get(1));
  }

  /** Creates a request for testing. */
  private DeliveryRequest newRequest(String id) {
    return new DeliveryRequest(id, "100 Main Street", "Package");
  }

  /** Broadcasts a request through the notification service. */
  private void broadcast(Shop shop, DeliveryRequest request) {
    new DeliveryNotificationService(shop).broadcastDeliveryRequest(request);
  }
}
