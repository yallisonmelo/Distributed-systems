package br.com.yfsmsystem.distributedsystems.productcentral.infrastructure.shared.constants;

public class CommonConstants {

  public static final String SUCCESS = "Success";
  public static final String OK = "OK";

  public static final String UTF_8 = "UTF-8";
  public static final String CREATED = "Created";

  public static final String DATE_OUT_REST_FORMAT = "dd-MM-yyyy hh:mm:ss";


  //Exceptions
  public static final String CATEGORY_NOT_FOUND = "Category not Found";
  public static final String CATEGORY_ALREADY_EXISISTS = "Category Already Exist";
  public static final String PRODUCT_ALREADY_EXISISTS = "Product Already Exist";
  public static final String PRODUCT_NOT_FOUND = "Product not Found";

  private CommonConstants() {
    throw new IllegalStateException("Utility Class");
  }

}