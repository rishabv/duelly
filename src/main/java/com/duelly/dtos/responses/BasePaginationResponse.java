package com.duelly.dtos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BasePaginationResponse<T> extends BaseApiResponse<T> {
    private Integer pageSize;
    private Integer pageNumber;
    private Integer totalPages;

  public BasePaginationResponse(T data, Integer pageSize, Integer pageNumber, Integer totalPages){
    super(data);
    this.pageNumber = pageNumber;
    this.pageSize = pageSize;
    this.totalPages = totalPages;
  }
}
