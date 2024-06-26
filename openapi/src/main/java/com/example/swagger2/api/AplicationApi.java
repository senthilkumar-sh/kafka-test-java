/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.3.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package com.example.swagger2.api;

import com.example.swagger2.model.Application;
import com.example.swagger2.model.DefineApplicationRequest;
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
public interface AplicationApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /aplication : Define a new application releases
     * Define a new application
     *
     * @param defineApplicationRequest  (required)
     * @return Successful operation (status code 200)
     *         or Invalid input (status code 400)
     *         or Validation exception (status code 422)
     */
    @Operation(
        operationId = "defineApplication",
        summary = "Define a new application releases",
        description = "Define a new application",
        tags = { "Application" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Application.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "422", description = "Validation exception")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/aplication",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Application> defineApplication(
        @Parameter(name = "DefineApplicationRequest", description = "", required = true) @Valid @RequestBody DefineApplicationRequest defineApplicationRequest
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


    /**
     * GET /aplication : Get all the applications
     * List all the applications.
     *
     * @param applications Tags to filter by (optional)
     * @return successful operation (status code 200)
     *         or Invalid app (status code 400)
     */
    @Operation(
        operationId = "getAllApplications",
        summary = "Get all the applications",
        description = "List all the applications.",
        tags = { "Application" },
        responses = {
            @ApiResponse(responseCode = "200", description = "successful operation", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Application.class))),
                @Content(mediaType = "application/xml", array = @ArraySchema(schema = @Schema(implementation = Application.class)))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid app")
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/aplication",
        produces = { "application/json", "application/xml" }
    )
    
    default ResponseEntity<List<Application>> getAllApplications(
        @Parameter(name = "applications", description = "Tags to filter by", in = ParameterIn.QUERY) @Valid @RequestParam(value = "applications", required = false) List<String> applications
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"components\" : [ { \"name\" : \"S3\", \"artifactTye\" : \"Terraform\", \"version\" : \"1.1\" }, { \"name\" : \"S3\", \"artifactTye\" : \"Terraform\", \"version\" : \"1.1\" } ], \"release\" : \"1.1\", \"name\" : \"Name\", \"id\" : \"uuid\" }, { \"components\" : [ { \"name\" : \"S3\", \"artifactTye\" : \"Terraform\", \"version\" : \"1.1\" }, { \"name\" : \"S3\", \"artifactTye\" : \"Terraform\", \"version\" : \"1.1\" } ], \"release\" : \"1.1\", \"name\" : \"Name\", \"id\" : \"uuid\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/xml"))) {
                    String exampleString = "<null> <id>uuid</id> <name>Name</name> <release>1.1</release> <components> </components> </null>";
                    ApiUtil.setExampleResponse(request, "application/xml", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /aplication : Update an existing application definition
     * Update an existing application definition by Id
     *
     * @param application Update an existent application definition (required)
     * @return Successful operation (status code 200)
     *         or Invalid ID supplied (status code 400)
     *         or Aplication not found (status code 404)
     *         or Validation exception (status code 422)
     */
    @Operation(
        operationId = "updateAplication",
        summary = "Update an existing application definition",
        description = "Update an existing application definition by Id",
        tags = { "Application" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Application.class))
            }),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Aplication not found"),
            @ApiResponse(responseCode = "422", description = "Validation exception")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/aplication",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Application> updateAplication(
        @Parameter(name = "Application", description = "Update an existent application definition", required = true) @Valid @RequestBody Application application
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
