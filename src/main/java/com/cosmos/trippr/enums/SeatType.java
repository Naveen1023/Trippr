package com.cosmos.trippr.enums;

public enum SeatType {
  CLASSIC(100.0),
  PREMIUM(150.0);

  private double seatPrice;

  SeatType(double price) {
    this.seatPrice = price;
  }

  public double getSeatPrice() {
    return this.seatPrice;
  }
}
