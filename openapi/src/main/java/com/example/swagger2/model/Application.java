package com.example.swagger2.model;

import java.net.URI;
import java.util.Objects;
import com.example.swagger2.model.Component;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Application
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-02T07:09:48.956959-05:00[America/Chicago]")
public class Application {

  private String id;

  private String name;

  private String release;

  @Valid
  private List<@Valid Component> components = new ArrayList<>();

  public Application() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Application(String name, String release, List<@Valid Component> components) {
    this.name = name;
    this.release = release;
    this.components = components;
  }

  public Application id(String id) {
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

  public Application name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @NotNull 
  @Schema(name = "name", example = "Name", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Application release(String release) {
    this.release = release;
    return this;
  }

  /**
   * Get release
   * @return release
  */
  @NotNull 
  @Schema(name = "release", example = "1.1", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("release")
  public String getRelease() {
    return release;
  }

  public void setRelease(String release) {
    this.release = release;
  }

  public Application components(List<@Valid Component> components) {
    this.components = components;
    return this;
  }

  public Application addComponentsItem(Component componentsItem) {
    if (this.components == null) {
      this.components = new ArrayList<>();
    }
    this.components.add(componentsItem);
    return this;
  }

  /**
   * Get components
   * @return components
  */
  @NotNull @Valid 
  @Schema(name = "components", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("components")
  public List<@Valid Component> getComponents() {
    return components;
  }

  public void setComponents(List<@Valid Component> components) {
    this.components = components;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Application application = (Application) o;
    return Objects.equals(this.id, application.id) &&
        Objects.equals(this.name, application.name) &&
        Objects.equals(this.release, application.release) &&
        Objects.equals(this.components, application.components);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, release, components);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Application {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    release: ").append(toIndentedString(release)).append("\n");
    sb.append("    components: ").append(toIndentedString(components)).append("\n");
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

