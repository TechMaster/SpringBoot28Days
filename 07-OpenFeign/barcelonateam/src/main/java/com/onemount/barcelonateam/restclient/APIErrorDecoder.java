package com.onemount.barcelonateam.restclient;
import java.io.IOException;

import com.onemount.barcelonateam.exceptions.APIError;
import com.onemount.barcelonateam.exceptions.APIException;
import com.onemount.barcelonateam.exceptions.TeamException;

import org.springframework.http.HttpStatus;

import feign.Response;
import feign.codec.Decoder;
import feign.codec.ErrorDecoder;


public class APIErrorDecoder implements ErrorDecoder {

  final Decoder decoder;
  final ErrorDecoder defaultDecoder = new ErrorDecoder.Default();

  public APIErrorDecoder(Decoder decoder) {
    this.decoder = decoder;
  }

  @Override
  public Exception decode(String methodKey, Response response) {
    try {
      APIError apiError = (APIError) decoder.decode(response, APIError.class);

      return new APIException(HttpStatus.valueOf(response.status()), 
      apiError.message, apiError.details);
      
    } catch (final IOException fallbackToDefault) {
      return defaultDecoder.decode(methodKey, response);
    }
  }
}