package com.sameer.order.DTO;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
)
{
}
