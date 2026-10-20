/**
 * Name: Tiansheng Fu
 * Course: CS-665 Software Design & Patterns
 * Date: 10/19/2026
 * File Name: DeliveryRequest.java
 * Description: Represents a product delivery request.
 * tutorial on throwing exceptions: https://docs.oracle.com/javase/tutorial/essential/exceptions/throwing.html
 * Java 8 IllegalArgumentException API: https://docs.oracle.com/javase/8/docs/api/java/lang/IllegalArgumentException.html
 */

package edu.bu.met.cs665;

/** Stores the details of an immutable delivery request. */
public final class DeliveryRequest {
  private final String requestId;
  private final String deliveryAddress;
  private final String productDescription;

  /** Creates a delivery request after validating its details. */
  public DeliveryRequest(String requestId, String deliveryAddress,
      String productDescription) {
    this.requestId = requireNonBlank(requestId, "Request ID");
    this.deliveryAddress = requireNonBlank(deliveryAddress, "Delivery address");
    this.productDescription = requireNonBlank(productDescription, "Product description");
  }

  /** Validates a required string. */
  private static String requireNonBlank(String value, String fieldName) {
    if (value == null || value.trim().isEmpty()) {
      throw new IllegalArgumentException(fieldName + " must not be blank.");
    }
    return value;
  }

  /** Returns the request identifier. */
  public String getRequestId() {
    return requestId;
  }

  /** Returns the delivery address. */
  public String getDeliveryAddress() {
    return deliveryAddress;
  }

  /** Returns the product description. */
  public String getProductDescription() {
    return productDescription;
  }
}
