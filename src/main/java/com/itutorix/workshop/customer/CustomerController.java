package com.itutorix.workshop.customer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/customers")
@Tag(name = "Customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Operation(
            description = "Get Endpoint for Customers",
            summary = "Fetch all Customers",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403",
                            content = @Content
                    )
            }
    )
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }


    @Operation(
            description = "Get Endpoint for a Single Customers",
            summary = "Fetch a Customer",
            responses = {
                    @ApiResponse(
                            description = "Customer Found",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = Customer.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403",
                            content = @Content
                    ),
                    @ApiResponse(
                            description = "Customer not Found",
                            responseCode = "404",
                            content = @Content
                    )
            }
    )
    @GetMapping("{customerId}")
    public ResponseEntity<CustomerDTO> getSingleCustomer(@PathVariable("customerId") Integer id) {
        return ResponseEntity.ok(customerService.getSingleCustomer(id));
    }

    @DeleteMapping("{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") Integer id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{customerId}")
    public ResponseEntity<?> updateCustomer(
            @PathVariable("customerId") Integer id,
            @RequestBody @Valid NewCustomerRequest request
    ) {
        customerService.updateCustomer(id, request);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<NewUserResponse> createCustomer(@RequestBody NewCustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @GetMapping("error")
    public ResponseEntity<?> throwException() {
        return ResponseEntity.ok().body(customerService.throwException());
    }
}