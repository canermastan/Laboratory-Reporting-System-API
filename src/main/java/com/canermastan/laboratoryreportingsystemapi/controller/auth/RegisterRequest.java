package com.canermastan.laboratoryreportingsystemapi.controller.auth;

public record RegisterRequest(String firstName, String lastName, String email, String password) {
}