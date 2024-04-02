package com.example.swagger2.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Environment
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-02T07:09:48.956959-05:00[America/Chicago]")
public class Environment {

  private String id;

  private String appRelease;

  private String awsAccount;

  private String namespace;

  public Environment() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Environment(String appRelease, String awsAccount, String namespace) {
    this.appRelease = appRelease;
    this.awsAccount = awsAccount;
    this.namespace = namespace;
  }

  public Environment id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", example = "uuid", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Environment appRelease(String appRelease) {
    this.appRelease = appRelease;
    return this;
  }

  /**
   * Get appRelease
   * @return appRelease
  */
  @NotNull 
  @Schema(name = "appRelease", example = "xyz-1.1", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("appRelease")
  public String getAppRelease() {
    return appRelease;
  }

  public void setAppRelease(String appRelease) {
    this.appRelease = appRelease;
  }

  public Environment awsAccount(String awsAccount) {
    this.awsAccount = awsAccount;
    return this;
  }

  /**
   * Get awsAccount
   * @return awsAccount
  */
  @NotNull 
  @Schema(name = "awsAccount", example = "abc", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("awsAccount")
  public String getAwsAccount() {
    return awsAccount;
  }

  public void setAwsAccount(String awsAccount) {
    this.awsAccount = awsAccount;
  }

  public Environment namespace(String namespace) {
    this.namespace = namespace;
    return this;
  }

  /**
   * Get namespace
   * @return namespace
  */
  @NotNull 
  @Schema(name = "namespace", example = "xyz-ns", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("namespace")
  public String getNamespace() {
    return namespace;
  }

  public void setNamespace(String namespace) {
    this.namespace = namespace;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Environment environment = (Environment) o;
    return Objects.equals(this.id, environment.id) &&
        Objects.equals(this.appRelease, environment.appRelease) &&
        Objects.equals(this.awsAccount, environment.awsAccount) &&
        Objects.equals(this.namespace, environment.namespace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, appRelease, awsAccount, namespace);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Environment {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    appRelease: ").append(toIndentedString(appRelease)).append("\n");
    sb.append("    awsAccount: ").append(toIndentedString(awsAccount)).append("\n");
    sb.append("    namespace: ").append(toIndentedString(namespace)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

