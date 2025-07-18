package com.tuorjp.financial_helper.enums;

public enum CategoryType {
  ENTRADA(0),
  SAIDA(1);

  private final int value;

  CategoryType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public static boolean isValidType(int value) {
    return value == ENTRADA.getValue() || value == SAIDA.getValue();
  }
}
