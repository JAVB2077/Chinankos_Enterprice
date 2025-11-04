package chinanko.chinanko.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for handling incoming requests related to a Town.
 * It uses Jakarta Validation annotations to ensure data integrity and
 * Jackson annotations for mapping JSON properties.
 */
@Data // Lombok: Automatically generates getters, setters, toString(), equals(), and hashCode().
@Builder // Lombok: Implements the Builder pattern for easier object instantiation.
public class TownRequest {

    /**
     * The name of the town.
     * - @NotBlank: Ensures the name is not null and not just whitespace.
     * - @Size: Restricts the name to a maximum of 100 characters.
     * - @JsonProperty: Maps the JSON property "name of the town" to this 'nameTown' field.
     */
    @NotBlank(message = "The town name cannot be empty.")
    @Size(max = 100, message = "The town name cannot exceed 100 characters.")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "The town name can only contain letters and spaces.") 
    @JsonProperty("name of the town")
    private String nameTown;

    /**
     * The geographic longitude of the town.
     * - Not annotated with @NotNull because the database allows NULL values for this field.
     * - @DecimalMin/-Max: Constrains the value to the valid geographic range for longitude.
     * - @Digits: Validates the precision, allowing up to 3 integer digits (e.g., -180)
     * and 10 fractional digits.
     */
    @DecimalMin(value = "-180.0", message = "Minimum longitude is -180.")
    @DecimalMax(value = "180.0", message = "Maximum longitude is 180.")
    @Digits(integer = 3, fraction = 10, message = "Invalid longitude format.")
    private BigDecimal longitude;

    /**
     * The geographic latitude of the town.
     * - Not annotated with @NotNull because the database allows NULL values for this field.
     * - @DecimalMin/-Max: Constrains the value to the valid geographic range for latitude.
     * - @Digits: Validates the precision, allowing up to 2 integer digits (e.g., 90)
     * and 10 fractional digits.
     */
    @DecimalMin(value = "-90.0", message = "Minimum latitude is -90.")
    @DecimalMax(value = "90.0", message = "Maximum latitude is 90.")
    @Digits(integer = 2, fraction = 10, message = "Invalid latitude format.")
    private BigDecimal latitude;

    /**
     * The foreign key ID linking this town to its corresponding State.
     * - @NotNull: This field is mandatory and cannot be null in the request.
     * - @JsonProperty: Maps the JSON property "State Id" to this 'stateId' field.
     */
    @NotNull(message = "The state ID (stateId) cannot be null.")
    @JsonProperty("State Id")
    private Integer stateId;
}