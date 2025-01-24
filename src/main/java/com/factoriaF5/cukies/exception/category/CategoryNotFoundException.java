package com.factoriaF5.cukies.exception.category;

import com.factoriaF5.cukies.exception.ApiException;
import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends ApiException {
  private final String searchField;
  private final String searchValue;

  //si no se encuentra por nombre
  public CategoryNotFoundException(String searchField, String searchValue) {
    super("category with " + searchField + " " + searchValue + " not found.", HttpStatus.NOT_FOUND);
    this.searchField = searchField;
    this.searchValue = searchValue;
  }

  public CategoryNotFoundException(String searchField, int searchValue) {
    super("Category with " + searchField + " " + searchValue + " not found.", HttpStatus.NOT_FOUND);
    this.searchField = searchField;
    this.searchValue = String.valueOf(searchValue);
  }
}
