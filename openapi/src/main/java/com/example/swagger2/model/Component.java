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
 * Component
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-02T07:09:48.956959-05:00[America/Chicago]")
public class Component {

  private String name;

  private String artifactTye;

  private String version;

  public Component name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  
  @Schema(name = "name", example = "S3", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Component artifactTye(String artifactTye) {
    this.artifactTye = artifactTye;
    return this;
  }

  /**
   * Get artifactTye
   * @return artifactTye
  */
  
  @Schema(name = "artifactTye", example = "Terraform", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("artifactTye")
  public String getArtifactTye() {
    return artifactTye;
  }

  public void setArtifactTye(String artifactTye) {
    this.artifactTye = artifactTye;
  }

  public Component version(String version) {
    this.version = version;
    return this;
  }

  /**
   * Get version
   * @return version
  */
  
  @Schema(name = "version", example = "1.1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("version")
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Component component = (Component) o;
    return Objects.equals(this.name, component.name) &&
        Objects.equals(this.artifactTye, component.artifactTye) &&
        Objects.equals(this.version, component.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, artifactTye, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Component {\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    artifactTye: ").append(toIndentedString(artifactTye)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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

