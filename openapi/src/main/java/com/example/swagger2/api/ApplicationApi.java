/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.example.swagger2.api;

import com.example.swagger2.model.Application;
import com.example.swagger2.model.Component;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-04-02T07:09:48.956959-05:00[America/Chicago]")
@Validated
@Tag(name = "Application", description = "Everything about your Apps")
public interface ApplicationApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * DELETE /application/{appId} : Deletes an application definition
     * delete a application definition
     *
     * @param appId Application Definition id to delete (required)
     * @return Invalid application definition value (status code 400)
     */
    @Operation(
        operationId = "deleteAppDef",
        summary = "Deletes an application definition",
        description = "delete a application definition",
        tags = { "Application" },
        responses = {
            @ApiResponse(responseCode = "400", description = "Invalid application definition value")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/application/{appId}"
    )
    
    default ResponseEntity<Void> deleteAppDef(
        @Parameter(name = "appId", description = "Application Definition id to delete", required = true, in = ParameterIn.PATH) @PathVariable("appId") Long appId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /application/components : Get all the components
     * List all the components.
     *
     * @param components Tags to filter by (optional)
     * @return successful operation (status code 200)
     *         or Invalid component (status code 400)
     */
    @Operation(
        operationId = "getAllComponents",
        summary = "Get all the components",
        description = "List all the components.",
        tags = { "Application" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Component.class))),
                @Content(mediaType = "application/xml", array = @ArraySchema(schema = @Schema(implementation = Component.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid component")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/application/components",
        produces = { "application/json", "application/xml" }
    )
    
    default ResponseEntity<List<Component>> getAllComponents(
        @Parameter(name = "components", description = "Tags to filter by", in = ParameterIn.QUERY) @Valid @RequestParam(value = "components", required = false) List<String> components
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"name\" : \"S3\", \"artifactTye\" : \"Terraform\", \"version\" : \"1.1\" }, { \"name\" : \"S3\", \"artifactTye\" : \"Terraform\", \"version\" : \"1.1\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/xml"))) {
                    String exampleString = "<null> <name>S3</name> <artifactTye>Terraform</artifactTye> <version>1.1</version> </null>";
                    ApiUtil.setExampleResponse(request, "application/xml", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /application/{appId} : Find application definition by Id
     * Returns a single application definition
     *
     * @param appId Id of application definition to return (required)
     * @return successful operation (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Environments not found (status code 404)
     */
    @Operation(
        operationId = "getAppDefById",
        summary = "Find application definition by Id",
        description = "Returns a single application definition",
        tags = { "Application" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Application.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Environments not found")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/application/{appId}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<Application> getAppDefById(
        @Parameter(name = "appId", description = "Id of application definition to return", required = true, in = ParameterIn.PATH) @PathVariable("appId") Long appId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"components\" : [ { \"name\" : \"S3\", \"artifactTye\" : \"Terraform\", \"version\" : \"1.1\" }, { \"name\" : \"S3\", \"artifactTye\" : \"Terraform\", \"version\" : \"1.1\" } ], \"release\" : \"1.1\", \"name\" : \"Name\", \"id\" : \"uuid\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}