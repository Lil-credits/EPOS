package io.epos.portal_api.api.educationModule.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetAllEducationModuleResponse {

  @JsonProperty(value = "total")
  private int total;

  @JsonProperty(value = "limit")
  private int limit;

  @JsonProperty(value = "page")
  private int page;

  @JsonProperty(value = "educationModules")
  private List<GetEducationModuleResponse> educationModules;

  public GetAllEducationModuleResponse() {
  }

  public GetAllEducationModuleResponse(int total, int page, int limit, List<GetEducationModuleResponse> educationModules) {
    this.total = total;
    this.page = page;
    this.limit = limit;
    this.educationModules = educationModules;
  }
  public List<GetEducationModuleResponse> getEducationModules() {
    return educationModules;
  }

}
